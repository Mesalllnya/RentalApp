/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rentalapp.dao;

import rentalapp.model.Transaksi;
import java.util.List;

public interface TransaksiDAO {
    void insert(Transaksi transaksi);
    void update(Transaksi transaksi);
    void delete(String idTransaksi);
    Transaksi getById(String idTransaksi);
    List<Transaksi> getAll();
}
