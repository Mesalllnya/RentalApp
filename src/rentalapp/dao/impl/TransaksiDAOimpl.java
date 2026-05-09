/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.dao.impl;

import rentalapp.config.DbConn;
import rentalapp.dao.TransaksiDAO;
import rentalapp.model.Transaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAOimpl implements TransaksiDAO {
    private Connection connection;

    public TransaksiDAOimpl() {
        this.connection = DbConn.getConnection();
    }

    @Override
    public void insert(Transaksi transaksi) {
        String sql = "INSERT INTO Transaksi (Id_Transaksi, Id_User, Id_Peminjam, Tgl_Pinjam, Tgl_pengembalian, Status, Total_Biaya) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transaksi.getIdTransaksi());
            stmt.setString(2, transaksi.getIdUser());
            stmt.setString(3, transaksi.getIdPeminjam());
            stmt.setDate(4, transaksi.getTglPinjam());
            stmt.setDate(5, transaksi.getTglPengembalian());
            stmt.setString(6, transaksi.getStatus());
            stmt.setDouble(7, transaksi.getTotalBiaya());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Transaksi transaksi) {
        String sql = "UPDATE Transaksi SET Id_User = ?, Id_Peminjam = ?, Tgl_Pinjam = ?, Tgl_pengembalian = ?, Status = ?, Total_Biaya = ? WHERE Id_Transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transaksi.getIdUser());
            stmt.setString(2, transaksi.getIdPeminjam());
            stmt.setDate(3, transaksi.getTglPinjam());
            stmt.setDate(4, transaksi.getTglPengembalian());
            stmt.setString(5, transaksi.getStatus());
            stmt.setDouble(6, transaksi.getTotalBiaya());
            stmt.setString(7, transaksi.getIdTransaksi());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String idTransaksi) {
        String sql = "DELETE FROM Transaksi WHERE Id_Transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaksi getById(String idTransaksi) {
        Transaksi transaksi = null;
        String sql = "SELECT * FROM Transaksi WHERE Id_Transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaksi = new Transaksi(
                    rs.getString("Id_Transaksi"),
                    rs.getString("Id_User"),
                    rs.getString("Id_Peminjam"),
                    rs.getDate("Tgl_Pinjam"),
                    rs.getDate("Tgl_pengembalian"),
                    rs.getString("Status"),
                    rs.getDouble("Total_Biaya")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaksi;
    }

    @Override
    public List<Transaksi> getAll() {
        List<Transaksi> listTransaksi = new ArrayList<>();
        String sql = "SELECT * FROM Transaksi";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(
                    rs.getString("Id_Transaksi"),
                    rs.getString("Id_User"),
                    rs.getString("Id_Peminjam"),
                    rs.getDate("Tgl_Pinjam"),
                    rs.getDate("Tgl_pengembalian"),
                    rs.getString("Status"),
                    rs.getDouble("Total_Biaya")
                );
                listTransaksi.add(transaksi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTransaksi;
    }
}