package rentalapp.controller;

import rentalapp.config.DbConn;
import rentalapp.dao.BarangDAO;
import rentalapp.dao.DetailPengembalianDAO;
import rentalapp.dao.PengembalianDAO;
import rentalapp.dao.TransaksiDAO;
import rentalapp.dao.impl.*;
import rentalapp.model.Barang;
import rentalapp.model.DetailPengembalian;
import rentalapp.model.Pengembalian;
import rentalapp.model.Transaksi;

import java.sql.Connection;
import java.sql.Date; // Pastikan menggunakan java.sql.Date
import java.sql.SQLException;
import java.time.LocalDate; // Diperlukan untuk konversi dan perhitungan selisih
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PengembalianController {
    private TransaksiDAO transaksiDAO;
    private PengembalianDAO pengembalianDAO;
    private DetailPengembalianDAO detailPengembalianDAO;
    private BarangDAO barangDAO;
    private Connection connection;

    private static final double TARIF_DENDA_PER_HARI = 10000.0;

    public PengembalianController() {
        this.transaksiDAO = new TransaksiDAOimpl();
        this.pengembalianDAO = new PengembalianDAOimpl();
        this.detailPengembalianDAO = new DetailPengembalianDAOimpl();
        this.barangDAO = new BarangDAOimpl();
        this.connection = DbConn.getConnection();
    }

    public double prosesPengembalian(String idPengembalian, String idTransaksi, String idUserKasir, Date tglKembaliReal, List<DetailPengembalian> listKondisiBarang, List<String> listIdBarangDikembalikan, List<Integer> listQtyDikembalikan) {
        double dendaKeterlambatan = 0.0;
        double totalDendaKerusakan = 0.0;

        try {
            connection.setAutoCommit(false);

            // 1. Ambil data Transaksi untuk mengecek tenggat waktu
            Transaksi transaksi = transaksiDAO.getById(idTransaksi);
            if (transaksi == null) {
                throw new IllegalArgumentException("Data transaksi tidak ditemukan!");
            }

            // 2. Hitung Denda Keterlambatan
            Date tglBatasKembali = transaksi.getTglPengembalian();
            
            // Konversi java.sql.Date ke LocalDate untuk operasi matematika hari
            LocalDate batasKembaliLocal = tglBatasKembali.toLocalDate();
            LocalDate kembaliRealLocal = tglKembaliReal.toLocalDate();

            if (kembaliRealLocal.isAfter(batasKembaliLocal)) {
                long selisihHari = ChronoUnit.DAYS.between(batasKembaliLocal, kembaliRealLocal);
                dendaKeterlambatan = selisihHari * TARIF_DENDA_PER_HARI;
            }

            // 3. Simpan Header Pengembalian
            Pengembalian pengembalian = new Pengembalian(idPengembalian, idTransaksi, idUserKasir, tglKembaliReal, dendaKeterlambatan);
            pengembalianDAO.insert(pengembalian);

            // 4. Simpan Detail Kondisi (Kerusakan) jika ada
            for (DetailPengembalian detail : listKondisiBarang) {
                detailPengembalianDAO.insert(detail);
                totalDendaKerusakan += detail.getNominalDendaKerusakan();
            }

            // 5. Kembalikan Stok Barang
            for (int i = 0; i < listIdBarangDikembalikan.size(); i++) {
                String idBarang = listIdBarangDikembalikan.get(i);
                int qty = listQtyDikembalikan.get(i);

                Barang barang = barangDAO.getById(idBarang);
                int stokBaru = barang.getJumlahStok() + qty;
                barang.setJumlahStok(stokBaru);
                barangDAO.update(barang);
            }

            // 6. Update status transaksi menjadi "Dikembalikan"
            transaksi.setStatus("Dikembalikan");
            transaksiDAO.update(transaksi);

            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Proses pengembalian gagal: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dendaKeterlambatan + totalDendaKerusakan;
    }
}