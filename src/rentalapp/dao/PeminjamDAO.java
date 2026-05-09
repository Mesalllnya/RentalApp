/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rentalapp.dao;

import rentalapp.model.Peminjam;
import java.util.List;

public interface PeminjamDAO {
    void insert(Peminjam peminjam);
    void update(Peminjam peminjam);
    void delete(String idPeminjam);
    Peminjam getById(String idPeminjam);
    List<Peminjam> getAll();
}
