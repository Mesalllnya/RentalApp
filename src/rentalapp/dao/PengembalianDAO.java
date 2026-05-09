/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rentalapp.dao;

import rentalapp.model.Pengembalian;
import java.util.List;

public interface PengembalianDAO {
    void insert(Pengembalian pengembalian);
    void update(Pengembalian pengembalian);
    void delete(String idPengembalian);
    Pengembalian getById(String idPengembalian);
    List<Pengembalian> getAll();
}
