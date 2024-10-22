package pos.clothify.store.service.custom;

import javafx.collections.ObservableList;
import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.model.ProductHasOrder;
import pos.clothify.store.model.Return;
import pos.clothify.store.model.ViewOrder;
import pos.clothify.store.service.SuperService;

import java.util.List;

public interface ReturnItemService extends SuperService {
    Return findByOrderId(Integer orderId);
    ObservableList<ProductHasOrder> orderList(Integer orderId);
}
