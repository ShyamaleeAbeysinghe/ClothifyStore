package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ProductHasOrderEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

import java.util.List;

public interface ProductHasOrderDao extends CrudReporsitory<ProductHasOrderEntity> {
    List<ProductHasOrderEntity> findByOrderId(OrderEntity orderEntity);
}
