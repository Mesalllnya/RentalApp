/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rentalapp.dao;

import rentalapp.model.DetailTransaksi;
import java.util.List;

public interface DetailTransaksiDAO {
    void insert(DetailTransaksi detail);
    void deleteByTransaksiId(String idTransaksi);
    List<DetailTransaksi> getByTransaksiId(String idTransaksi);
}
