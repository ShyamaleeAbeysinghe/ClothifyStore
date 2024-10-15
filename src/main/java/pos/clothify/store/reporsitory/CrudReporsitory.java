package pos.clothify.store.reporsitory;

import java.util.List;

public interface CrudReporsitory<T> extends SuperDao {
    boolean save(T entity);
    boolean update(T entity);
    List <T> findAll();

}
