package pos.clothify.store.service.custom;

import javafx.collections.ObservableList;
import pos.clothify.store.model.SaveOrder;
import pos.clothify.store.service.SuperService;

public interface AddOrderServise extends SuperService {
    Integer findByMaxId();
    ObservableList<String> findAllProduvtName();
    Double getPrice(String name);

    Boolean saveOrder(SaveOrder saveOrder);
}
