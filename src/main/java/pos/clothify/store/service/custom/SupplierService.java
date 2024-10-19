package pos.clothify.store.service.custom;

import javafx.collections.ObservableList;
import pos.clothify.store.model.Supplier;
import pos.clothify.store.service.SuperService;

public interface SupplierService extends SuperService {
    Integer findByMaxId();

    Supplier findBySupplierEmail(String email);

    boolean addSupplier(Supplier supplier);

    ObservableList<Supplier> findAllSupplier();

    boolean updateUser(Supplier supplier);

    boolean deleteSupplier(String email);


}
