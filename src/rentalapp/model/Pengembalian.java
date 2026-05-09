/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.model;

import java.sql.Date;

public class Pengembalian {
    private String idPengembalian;
    private String idTransaksi;
    private String idUser; // Kasir yang menerima pengembalian
    private Date tglKembaliReal;
    private double dendaKeterlambatan;

    public Pengembalian() {}

    public Pengembalian(String idPengembalian, String idTransaksi, String idUser, Date tglKembaliReal, double dendaKeterlambatan) {
        this.idPengembalian = idPengembalian;
        this.idTransaksi = idTransaksi;
        this.idUser = idUser;
        this.tglKembaliReal = tglKembaliReal;
        this.dendaKeterlambatan = dendaKeterlambatan;
    }

    public String getIdPengembalian() { return idPengembalian; }
    public void setIdPengembalian(String idPengembalian) { this.idPengembalian = idPengembalian; }

    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public Date getTglKembaliReal() { return tglKembaliReal; }
    public void setTglKembaliReal(Date tglKembaliReal) { this.tglKembaliReal = tglKembaliReal; }

    public double getDendaKeterlambatan() { return dendaKeterlambatan; }
    public void setDendaKeterlambatan(double dendaKeterlambatan) { this.dendaKeterlambatan = dendaKeterlambatan; }
}
