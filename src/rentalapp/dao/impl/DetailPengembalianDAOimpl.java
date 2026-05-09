/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.dao.impl;

import rentalapp.config.DbConn;
import rentalapp.dao.DetailPengembalianDAO;
import rentalapp.model.DetailPengembalian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailPengembalianDAOimpl implements DetailPengembalianDAO {
    private Connection connection;

    public DetailPengembalianDAOimpl() {
        this.connection = DbConn.getConnection();
    }

    @Override
    public void insert(DetailPengembalian detail) {
        String sql = "INSERT INTO Detail_Pengembalian (Id_Detail_Kembali, Id_Pengembalian, Id_Detail, Kondisi_Barang, Nominal_Denda_Kerusakan) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, detail.getIdDetailKembali());
            stmt.setString(2, detail.getIdPengembalian());
            stmt.setString(3, detail.getIdDetail()); // Mengacu ke tabel Detail_Transaksi
            stmt.setString(4, detail.getKondisiBarang());
            stmt.setDouble(5, detail.getNominalDendaKerusakan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByPengembalianId(String idPengembalian) {
        String sql = "DELETE FROM Detail_Pengembalian WHERE Id_Pengembalian = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPengembalian);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetailPengembalian> getByPengembalianId(String idPengembalian) {
        List<DetailPengembalian> listDetail = new ArrayList<>();
        String sql = "SELECT * FROM Detail_Pengembalian WHERE Id_Pengembalian = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPengembalian);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DetailPengembalian detail = new DetailPengembalian(
                    rs.getString("Id_Detail_Kembali"),
                    rs.getString("Id_Pengembalian"),
                    rs.getString("Id_Detail"),
                    rs.getString("Kondisi_Barang"),
                    rs.getDouble("Nominal_Denda_Kerusakan")
                );
                listDetail.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDetail;
    }
}
