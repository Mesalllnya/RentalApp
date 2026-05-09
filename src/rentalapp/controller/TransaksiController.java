package rentalapp.controller;

import rentalapp.config.DbConn;
import rentalapp.dao.BarangDAO;
import rentalapp.dao.DetailTransaksiDAO;
import rentalapp.dao.TransaksiDAO;
import rentalapp.dao.impl.BarangDAOImpl;
import rentalapp.dao.impl.DetailTransaksiDAOImpl;
import rentalapp.dao.impl.TransaksiDAOImpl;
import rentalapp.model.Barang;
import rentalapp.model.DetailTransaksi;
import rentalapp.model.Transaksi;

import java.sql.Connection;
import java.sql.Date; // <-- Menggunakan java.sql.Date sesuai model Anda
import java.sql.SQLException;
import java.util.List;

public class TransaksiController {
    private TransaksiDAO transaksiDAO;
    private DetailTransaksiDAO detailTransaksiDAO;
    private BarangDAO barangDAO;
    private Connection connection;

    public TransaksiController() {
        this.transaksiDAO = new TransaksiDAOImpl();
        this.detailTransaksiDAO = new DetailTransaksiDAOImpl();
        this.barangDAO = new BarangDAOImpl();
        this.connection = DbConn.getConnection(); // <-- Menggunakan DbConn
    }

    // Method utama yang akan dipanggil tombol "Sewa" di GUI nanti
    public void prosesSewa(String idTransaksi, String idUser, String idPeminjam, Date tglPinjam, Date tglKembali, List<DetailTransaksi> keranjangSewa, double totalBiaya) {
        if (keranjangSewa == null || keranjangSewa.isEmpty()) {
            throw new IllegalArgumentException("Keranjang sewa tidak boleh kosong!");
        }

        try {
            // 1. Matikan auto-commit (Mulai blok transaksi)
            connection.setAutoCommit(false);

            // 2. Simpan Header Transaksi
            Transaksi transaksi = new Transaksi(idTransaksi, idUser, idPeminjam, tglPinjam, tglKembali, "Dipinjam", totalBiaya);
            transaksiDAO.insert(transaksi);

            // 3. Simpan isi keranjang (Detail Transaksi) dan Kurangi Stok Barang
            for (DetailTransaksi detail : keranjangSewa) {
                // Insert ke tabel Detail_Transaksi
                detailTransaksiDAO.insert(detail);

                // Tarik data barang saat ini dari database
                Barang barang = barangDAO.getById(detail.getIdBarang());
                if (barang == null) {
                    throw new Exception("Barang dengan ID " + detail.getIdBarang() + " tidak ditemukan!");
                }
                if (barang.getJumlahStok() < detail.getJumlah()) {
                    throw new Exception("Stok barang " + barang.getNamaBarang() + " tidak mencukupi!");
                }
                
                // Kalkulasi dan update stok barang yang baru
                int stokBaru = barang.getJumlahStok() - detail.getJumlah();
                barang.setJumlahStok(stokBaru);
                barangDAO.update(barang);
            }

            // 4. Jika semua langkah di atas berhasil, simpan permanen!
            connection.commit();

        } catch (Exception e) {
            try {
                // Jika ADA SATU SAJA yang gagal (misal stok habis di tengah jalan), batalkan SEMUANYA
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // Lemparkan error ke GUI agar bisa ditampilkan di JOptionPane
            throw new RuntimeException("Transaksi gagal dan dibatalkan: " + e.getMessage());
        } finally {
            try {
                // Kembalikan ke mode normal
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}