/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.dao.impl;

import rentalapp.config.DbConn;
import rentalapp.dao.PengembalianDAO;
import rentalapp.model.Pengembalian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PengembalianDAOimpl implements PengembalianDAO {
    private Connection connection;

    public PengembalianDAOimpl() {
        this.connection = DbConn.getConnection();
    }

    @Override
    public void insert(Pengembalian pengembalian) {
        String sql = "INSERT INTO Pengembalian (Id_Pengembalian, Id_Transaksi, Id_User, Tgl_Kembali_Real, Denda_Keterlambatan) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pengembalian.getIdPengembalian());
            stmt.setString(2, pengembalian.getIdTransaksi());
            stmt.setString(3, pengembalian.getIdUser());
            stmt.setDate(4, pengembalian.getTglKembaliReal());
            stmt.setDouble(5, pengembalian.getDendaKeterlambatan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pengembalian pengembalian) {
        String sql = "UPDATE Pengembalian SET Id_Transaksi = ?, Id_User = ?, Tgl_Kembali_Real = ?, Denda_Keterlambatan = ? WHERE Id_Pengembalian = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pengembalian.getIdTransaksi());
            stmt.setString(2, pengembalian.getIdUser());
            stmt.setDate(3, pengembalian.getTglKembaliReal());
            stmt.setDouble(4, pengembalian.getDendaKeterlambatan());
            stmt.setString(5, pengembalian.getIdPengembalian());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String idPengembalian) {
        String sql = "DELETE FROM Pengembalian WHERE Id_Pengembalian = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPengembalian);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pengembalian getById(String idPengembalian) {
        Pengembalian pengembalian = null;
        String sql = "SELECT * FROM Pengembalian WHERE Id_Pengembalian = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPengembalian);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pengembalian = new Pengembalian(
                    rs.getString("Id_Pengembalian"),
                    rs.getString("Id_Transaksi"),
                    rs.getString("Id_User"),
                    rs.getDate("Tgl_Kembali_Real"),
                    rs.getDouble("Denda_Keterlambatan")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pengembalian;
    }

    @Override
    public List<Pengembalian> getAll() {
        List<Pengembalian> listPengembalian = new ArrayList<>();
        String sql = "SELECT * FROM Pengembalian";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pengembalian pengembalian = new Pengembalian(
                    rs.getString("Id_Pengembalian"),
                    rs.getString("Id_Transaksi"),
                    rs.getString("Id_User"),
                    rs.getDate("Tgl_Kembali_Real"),
                    rs.getDouble("Denda_Keterlambatan")
                );
                listPengembalian.add(pengembalian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPengembalian;
    }
}
