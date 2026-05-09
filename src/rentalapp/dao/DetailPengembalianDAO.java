/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rentalapp.dao;

import rentalapp.model.DetailPengembalian;
import java.util.List;

public interface DetailPengembalianDAO {
    void insert(DetailPengembalian detail);
    void deleteByPengembalianId(String idPengembalian);
    List<DetailPengembalian> getByPengembalianId(String idPengembalian);
}
