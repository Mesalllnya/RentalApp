/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.dao.impl;

import rentalapp.config.DbConn;
import rentalapp.dao.DetailTransaksiDAO;
import rentalapp.model.DetailTransaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailTransaksiDAOimpl implements DetailTransaksiDAO {
    private Connection connection;

    public DetailTransaksiDAOimpl() {
        this.connection = DbConn.getConnection();
    }

    @Override
    public void insert(DetailTransaksi detail) {
        String sql = "INSERT INTO Detail_Transaksi (Id_Detail, Id_Transaksi, Id_Barang, Jumlah, Harga_Satuan) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, detail.getIdDetail());
            stmt.setString(2, detail.getIdTransaksi());
            stmt.setString(3, detail.getIdBarang());
            stmt.setInt(4, detail.getJumlah());
            stmt.setDouble(5, detail.getHargaSatuan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByTransaksiId(String idTransaksi) {
        String sql = "DELETE FROM Detail_Transaksi WHERE Id_Transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetailTransaksi> getByTransaksiId(String idTransaksi) {
        List<DetailTransaksi> listDetail = new ArrayList<>();
        String sql = "SELECT * FROM Detail_Transaksi WHERE Id_Transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DetailTransaksi detail = new DetailTransaksi(
                    rs.getString("Id_Detail"),
                    rs.getString("Id_Transaksi"),
                    rs.getString("Id_Barang"),
                    rs.getInt("Jumlah"),
                    rs.getDouble("Harga_Satuan")
                );
                listDetail.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDetail;
    }
}
