package pos.clothify.store.service.custom;

import javafx.collections.ObservableList;
import pos.clothify.store.model.ViewOrder;
import pos.clothify.store.service.SuperService;

public interface ViewOrderService extends SuperService {
    ObservableList<ViewOrder> findAllOrders();

    ViewOrder findByOrderId(Integer orderId);

    Boolean returnOrder(Integer orderId);
}
