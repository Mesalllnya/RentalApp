package rentalapp.controller;

import rentalapp.dao.PeminjamDAO;
import rentalapp.dao.impl.PeminjamDAOimpl;
import rentalapp.model.Peminjam;

import java.util.List;

public class PeminjamController {
    private PeminjamDAO peminjamDAO;

    public PeminjamController() {
        this.peminjamDAO = new PeminjamDAOimpl();
    }

    public void tambahPeminjam(String idPeminjam, String nama, String kontak, String organisasi) {
        if (idPeminjam == null || idPeminjam.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Peminjam tidak boleh kosong!");
        }
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Peminjam tidak boleh kosong!");
        }
        if (kontak == null || kontak.trim().isEmpty()) {
            throw new IllegalArgumentException("Kontak tidak boleh kosong!");
        }

        Peminjam p = new Peminjam(idPeminjam, nama, kontak, organisasi);
        peminjamDAO.insert(p);
    }

    public void ubahPeminjam(String idPeminjam, String nama, String kontak, String organisasi) {
        if (idPeminjam == null || idPeminjam.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih Peminjam yang akan diubah terlebih dahulu!");
        }
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Peminjam tidak boleh kosong!");
        }

        Peminjam p = new Peminjam(idPeminjam, nama, kontak, organisasi);
        peminjamDAO.update(p);
    }

    public void hapusPeminjam(String idPeminjam) {
        if (idPeminjam == null || idPeminjam.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih Peminjam yang akan dihapus terlebih dahulu!");
        }
        peminjamDAO.delete(idPeminjam);
    }

    public List<Peminjam> getSemuaPeminjam() {
        return peminjamDAO.getAll();
    }

    public Peminjam cariPeminjam(String idPeminjam) {
        if (idPeminjam == null || idPeminjam.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Peminjam tidak valid!");
        }
        return peminjamDAO.getById(idPeminjam);
    }
}