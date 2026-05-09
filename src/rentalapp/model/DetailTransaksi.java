/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.model;

public class DetailTransaksi {
    private String idDetail;
    private String idTransaksi;
    private String idBarang;
    private int jumlah;
    private double hargaSatuan;

    public DetailTransaksi() {}

    public DetailTransaksi(String idDetail, String idTransaksi, String idBarang, int jumlah, double hargaSatuan) {
        this.idDetail = idDetail;
        this.idTransaksi = idTransaksi;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
    }

    public String getIdDetail() { return idDetail; }
    public void setIdDetail(String idDetail) { this.idDetail = idDetail; }

    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

    public String getIdBarang() { return idBarang; }
    public void setIdBarang(String idBarang) { this.idBarang = idBarang; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public double getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(double hargaSatuan) { this.hargaSatuan = hargaSatuan; }
}
