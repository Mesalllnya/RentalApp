/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.dao.impl;

import rentalapp.config.DbConn;
import rentalapp.dao.PeminjamDAO;
import rentalapp.model.Peminjam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeminjamDAOimpl implements PeminjamDAO {
    private Connection connection;

    public PeminjamDAOimpl() {
        this.connection = DbConn.getConnection();
    }

    @Override
    public void insert(Peminjam peminjam) {
        String sql = "INSERT INTO Peminjam (Id_Peminjam, Nama, Kontak, Organisasi) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, peminjam.getIdPeminjam());
            stmt.setString(2, peminjam.getNamaPeminjam());
            stmt.setString(3, peminjam.getKontak());
            stmt.setString(4, peminjam.getOrganisasi());
            stmt.executeUpdate();
            System.out.println("Data Peminjam berhasil disimpan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Peminjam peminjam) {
        String sql = "UPDATE Peminjam SET Nama = ?, Kontak = ?, Organisasi = ? WHERE Id_Peminjam = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, peminjam.getNamaPeminjam());
            stmt.setString(2, peminjam.getKontak());
            stmt.setString(3, peminjam.getOrganisasi());
            stmt.setString(4, peminjam.getIdPeminjam());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String idPeminjam) {
        String sql = "DELETE FROM Peminjam WHERE Id_Peminjam = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPeminjam);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Peminjam getById(String idPeminjam) {
        Peminjam peminjam = null;
        String sql = "SELECT * FROM Peminjam WHERE Id_Peminjam = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPeminjam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                peminjam = new Peminjam(
                    rs.getString("Id_Peminjam"),
                    rs.getString("Nama"),
                    rs.getString("Kontak"),
                    rs.getString("Organisasi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peminjam;
    }

    @Override
    public List<Peminjam> getAll() {
        List<Peminjam> listPeminjam = new ArrayList<>();
        String sql = "SELECT * FROM Peminjam";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Peminjam peminjam = new Peminjam(
                    rs.getString("Id_Peminjam"),
                    rs.getString("Nama"),
                    rs.getString("Kontak"),
                    rs.getString("Organisasi")
                );
                listPeminjam.add(peminjam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPeminjam;
    }
}
