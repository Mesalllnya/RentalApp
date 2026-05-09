/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rentalapp.dao;

import rentalapp.model.User;
import java.util.List;

public interface UserDAO {
    void insert(User user);
    void update(User user);
    void delete(String idUser);
    User getById(String idUser);
    List<User> getAll();

    public User login(String idUser, String password);
}
