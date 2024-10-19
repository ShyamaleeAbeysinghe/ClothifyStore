package pos.clothify.store.service.custom;

import javafx.collections.ObservableList;
import pos.clothify.store.model.Product;
import pos.clothify.store.model.User;
import pos.clothify.store.service.SuperService;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);

    Integer findByMaxId();

    ObservableList<String> getSupplierName();

    ObservableList<Product> getAllProducts();

    Product findProductName(String name);

    boolean updatProduct(Product product);

    boolean deleteProduct(String name);
}
