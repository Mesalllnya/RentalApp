

package rentalapp.dao.impl;

import rentalapp.config.DbConn;
import rentalapp.dao.UserDAO;
import rentalapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl implements UserDAO {
    private Connection connection;

    public UserDAOimpl() {
        this.connection = DbConn.getConnection();
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO User (Id_User, Nama, Role, Password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getIdUser());
            stmt.setString(2, user.getNama());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
            System.out.println("Data User berhasil disimpan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE User SET Nama = ?, Role = ?, Password = ? WHERE Id_User = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getRole());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getIdUser());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String idUser) {
        String sql = "DELETE FROM User WHERE Id_User = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idUser);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(String idUser) {
        User user = null;
        String sql = "SELECT * FROM User WHERE Id_User = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idUser);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getString("Id_User"),
                    rs.getString("Nama"),
                    rs.getString("Role"),
                    rs.getString("Password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                    rs.getString("Id_User"),
                    rs.getString("Nama"),
                    rs.getString("Role"),
                    rs.getString("Password")
                );
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }
}