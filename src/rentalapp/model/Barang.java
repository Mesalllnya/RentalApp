package rentalapp.model;

public class Barang {
    private String idBarang;
    private String namaBarang;
    private int jumlahStok;
    private int maintenance;
    private double hargaSewaPerHari;

    public Barang() {}

    public Barang(String idBarang, String namaBarang, int jumlahStok, int maintenance, double hargaSewaPerHari) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.jumlahStok = jumlahStok;
        this.maintenance = maintenance;
        this.hargaSewaPerHari = hargaSewaPerHari;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlahStok() {
        return jumlahStok;
    }

    public void setJumlahStok(int jumlahStok) {
        this.jumlahStok = jumlahStok;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public double getHargaSewaPerHari() {
        return hargaSewaPerHari;
    }

    public void setHargaSewaPerHari(double hargaSewaPerHari) {
        this.hargaSewaPerHari = hargaSewaPerHari;
    }
}