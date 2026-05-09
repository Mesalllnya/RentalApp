/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalapp.dao.impl;

import rentalapp.config.DbConn; // Menggunakan nama class config yang ada di gambar Anda
import rentalapp.dao.BarangDAO;
import rentalapp.model.Barang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarangDAOimpl {

    public class BarangDAOImpl implements BarangDAO {

        private Connection connection;

        public BarangDAOImpl() {
            // Memanggil koneksi dari class DbConn Anda
            this.connection = DbConn.getConnection();
        }

        @Override
        public void insert(Barang barang) {
            String sql = "INSERT INTO Barang (Id_Barang, Nama_Barang, Jumlah_Stok, Maintenance, Harga_Sewa_Per_Hari) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, barang.getIdBarang());
                stmt.setString(2, barang.getNamaBarang());
                stmt.setInt(3, barang.getJumlahStok());
                stmt.setInt(4, barang.getMaintenance());
                stmt.setDouble(5, barang.getHargaSewaPerHari());
                stmt.executeUpdate();
                System.out.println("Data barang berhasil disimpan!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void update(Barang barang) {
            String sql = "UPDATE Barang SET Nama_Barang = ?, Jumlah_Stok = ?, Maintenance = ?, Harga_Sewa_Per_Hari = ? WHERE Id_Barang = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, barang.getNamaBarang());
                stmt.setInt(2, barang.getJumlahStok());
                stmt.setInt(3, barang.getMaintenance());
                stmt.setDouble(4, barang.getHargaSewaPerHari());
                stmt.setString(5, barang.getIdBarang());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void delete(String idBarang) {
            String sql = "DELETE FROM Barang WHERE Id_Barang = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, idBarang);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Barang getById(String idBarang) {
            Barang barang = null;
            String sql = "SELECT * FROM Barang WHERE Id_Barang = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, idBarang);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    barang = new Barang(
                            rs.getString("Id_Barang"),
                            rs.getString("Nama_Barang"),
                            rs.getInt("Jumlah_Stok"),
                            rs.getInt("Maintenance"),
                            rs.getDouble("Harga_Sewa_Per_Hari")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return barang;
        }

        @Override
        public List<Barang> getAll() {
            List<Barang> listBarang = new ArrayList<>();
            String sql = "SELECT * FROM Barang";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Barang barang = new Barang(
                            rs.getString("Id_Barang"),
                            rs.getString("Nama_Barang"),
                            rs.getInt("Jumlah_Stok"),
                            rs.getInt("Maintenance"),
                            rs.getDouble("Harga_Sewa_Per_Hari")
                    );
                    listBarang.add(barang);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return listBarang;
        }
    }
}
