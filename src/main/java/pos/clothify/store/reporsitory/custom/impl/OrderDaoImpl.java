package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.reporsitory.custom.OrderDao;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity entity) {
        return false;
    }

    @Override
    public boolean update(OrderEntity entity) {
        return false;
    }

    @Override
    public List<OrderEntity> findAll() {
        return List.of();
    }
}
