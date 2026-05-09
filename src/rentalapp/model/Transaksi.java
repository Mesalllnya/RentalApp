/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.model;

import java.sql.Date;

public class Transaksi {
    private String idTransaksi;
    private String idUser;
    private String idPeminjam;
    private Date tglPinjam;
    private Date tglPengembalian;
    private String status;
    private double totalBiaya;

    public Transaksi() {}

    public Transaksi(String idTransaksi, String idUser, String idPeminjam, Date tglPinjam, Date tglPengembalian, String status, double totalBiaya) {
        this.idTransaksi = idTransaksi;
        this.idUser = idUser;
        this.idPeminjam = idPeminjam;
        this.tglPinjam = tglPinjam;
        this.tglPengembalian = tglPengembalian;
        this.status = status;
        this.totalBiaya = totalBiaya;
    }

    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getIdPeminjam() { return idPeminjam; }
    public void setIdPeminjam(String idPeminjam) { this.idPeminjam = idPeminjam; }

    public Date getTglPinjam() { return tglPinjam; }
    public void setTglPinjam(Date tglPinjam) { this.tglPinjam = tglPinjam; }

    public Date getTglPengembalian() { return tglPengembalian; }
    public void setTglPengembalian(Date tglPengembalian) { this.tglPengembalian = tglPengembalian; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalBiaya() { return totalBiaya; }
    public void setTotalBiaya(double totalBiaya) { this.totalBiaya = totalBiaya; }
}
