package rentalapp.controller;

import rentalapp.dao.BarangDAO;
import rentalapp.dao.impl.BarangDAOImpl;
import rentalapp.model.Barang;

import java.util.List;

public class BarangController {
    // Controller memanggil interface DAO, bukan implementasinya secara langsung (Polymorphism)
    private BarangDAO barangDAO;

    public BarangController() {
        this.barangDAO = new BarangDAOImpl();
    }

    // 1. Method untuk menambah data (Dipanggil saat tombol "Simpan" di-klik di GUI)
    public void tambahBarang(String idBarang, String namaBarang, int jumlahStok, int maintenance, double hargaSewa) {
        // Logika Bisnis & Validasi (Contoh: ID dan Nama tidak boleh kosong)
        if (idBarang == null || idBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Barang tidak boleh kosong!");
        }
        if (namaBarang == null || namaBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Barang tidak boleh kosong!");
        }
        if (hargaSewa <= 0) {
            throw new IllegalArgumentException("Harga sewa harus lebih dari 0!");
        }

        // Bungkus data ke dalam objek Model
        Barang barang = new Barang(idBarang, namaBarang, jumlahStok, maintenance, hargaSewa);
        
        // Suruh DAO untuk insert ke database
        barangDAO.insert(barang);
    }

    // 2. Method untuk mengubah data (Dipanggil saat tombol "Ubah/Update" di-klik)
    public void ubahBarang(String idBarang, String namaBarang, int jumlahStok, int maintenance, double hargaSewa) {
        if (idBarang == null || idBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih barang yang akan diubah terlebih dahulu!");
        }
        
        Barang barang = new Barang(idBarang, namaBarang, jumlahStok, maintenance, hargaSewa);
        barangDAO.update(barang);
    }

    // 3. Method untuk menghapus data (Dipanggil saat tombol "Hapus" di-klik)
    public void hapusBarang(String idBarang) {
        if (idBarang == null || idBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih barang yang akan dihapus terlebih dahulu!");
        }
        barangDAO.delete(idBarang);
    }

    // 4. Method untuk mengambil semua data (Dipanggil saat GUI pertama kali dibuka untuk mengisi JTable)
    public List<Barang> getSemuaBarang() {
        return barangDAO.getAll();
    }

    // 5. Method untuk mencari barang spesifik (Bisa digunakan untuk fitur Search nanti)
    public Barang cariBarang(String idBarang) {
        return barangDAO.getById(idBarang);
    }
}