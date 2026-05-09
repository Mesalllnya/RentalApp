package rentalapp.controller;

import rentalapp.dao.UserDAO;
import rentalapp.dao.impl.UserDAOimpl;
import rentalapp.model.User;

import java.util.List;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOimpl();
    }

    public User prosesLogin(String idUser, String password) {
        if (idUser == null || idUser.trim().isEmpty()) {
            throw new IllegalArgumentException("ID User tidak boleh kosong!");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong!");
        }
        
        User user = userDAO.login(idUser, password);
        if (user == null) {
            throw new IllegalArgumentException("ID User atau Password salah!");
        }
        return user;
    }

    public void tambahUser(String idUser, String nama, String role, String password) {
        if (idUser == null || idUser.trim().isEmpty()) {
            throw new IllegalArgumentException("ID User tidak boleh kosong!");
        }
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong!");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong!");
        }
        
        User user = new User(idUser, nama, role, password);
        userDAO.insert(user);
    }

    public void ubahUser(String idUser, String nama, String role, String password) {
        if (idUser == null || idUser.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih User yang akan diubah terlebih dahulu!");
        }
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong!");
        }
        
        User user = new User(idUser, nama, role, password);
        userDAO.update(user);
    }

    public void hapusUser(String idUser) {
        if (idUser == null || idUser.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih User yang akan dihapus terlebih dahulu!");
        }
        userDAO.delete(idUser);
    }

    public List<User> getSemuaUser() {
        return userDAO.getAll();
    }

    public User cariUser(String idUser) {
        if (idUser == null || idUser.trim().isEmpty()) {
            throw new IllegalArgumentException("ID User tidak valid!");
        }
        return userDAO.getById(idUser);
    }
}