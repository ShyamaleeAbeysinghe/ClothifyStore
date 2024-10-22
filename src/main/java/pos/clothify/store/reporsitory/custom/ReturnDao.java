package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ReturnEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

public interface ReturnDao extends CrudReporsitory<ReturnEntity> {
    ReturnEntity findByOrderId(OrderEntity orderEntity);

}
