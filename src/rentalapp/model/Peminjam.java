package rentalapp.model;

public class Peminjam {
    private String idPeminjam;
    private String nama;
    private String kontak;
    private String organisasi;

    public Peminjam() {}

    public Peminjam(String idPeminjam, String nama, String kontak, String organisasi) {
        this.idPeminjam = idPeminjam;
        this.nama = nama;
        this.kontak = kontak;
        this.organisasi = organisasi;
    }

    public String getIdPeminjam() {
        return idPeminjam;
    }

    public void setIdPeminjam(String idPeminjam) {
        this.idPeminjam = idPeminjam;
    }

    public String getNamaPeminjam() {
        return nama;
    }

    public void setNamaPeminjam(String nama) {
        this.nama = nama;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getOrhanisasi() {
        return organisasi;
    }

    public void setOrganisasi(String organisasi) {
        this.organisasi = organisasi;
    }
}