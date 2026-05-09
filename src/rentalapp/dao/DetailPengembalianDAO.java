
package rentalapp.dao;

import rentalapp.model.DetailPengembalian;
import java.util.List;

public interface DetailPengembalianDAO {
    void insert(DetailPengembalian detail);
    void deleteByPengembalianId(String idPengembalian);
    List<DetailPengembalian> getByPengembalianId(String idPengembalian);
}
