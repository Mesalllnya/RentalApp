/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.model;

public class DetailPengembalian {
    private String idDetailKembali;
    private String idPengembalian;
    private String idDetail; // Merujuk ke Id_Detail di Detail_Transaksi
    private String kondisiBarang;
    private double nominalDendaKerusakan;

    public DetailPengembalian() {}

    public DetailPengembalian(String idDetailKembali, String idPengembalian, String idDetail, String kondisiBarang, double nominalDendaKerusakan) {
        this.idDetailKembali = idDetailKembali;
        this.idPengembalian = idPengembalian;
        this.idDetail = idDetail;
        this.kondisiBarang = kondisiBarang;
        this.nominalDendaKerusakan = nominalDendaKerusakan;
    }

    public String getIdDetailKembali() { return idDetailKembali; }
    public void setIdDetailKembali(String idDetailKembali) { this.idDetailKembali = idDetailKembali; }

    public String getIdPengembalian() { return idPengembalian; }
    public void setIdPengembalian(String idPengembalian) { this.idPengembalian = idPengembalian; }

    public String getIdDetail() { return idDetail; }
    public void setIdDetail(String idDetail) { this.idDetail = idDetail; }

    public String getKondisiBarang() { return kondisiBarang; }
    public void setKondisiBarang(String kondisiBarang) { this.kondisiBarang = kondisiBarang; }

    public double getNominalDendaKerusakan() { return nominalDendaKerusakan; }
    public void setNominalDendaKerusakan(double nominalDendaKerusakan) { this.nominalDendaKerusakan = nominalDendaKerusakan; }
}
