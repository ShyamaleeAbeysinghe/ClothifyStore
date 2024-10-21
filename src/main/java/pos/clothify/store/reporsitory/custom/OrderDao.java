package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

public interface OrderDao extends CrudReporsitory<OrderEntity> {
    Integer findByMaxId();
    OrderEntity findByOrderId(Integer orderId);
}
