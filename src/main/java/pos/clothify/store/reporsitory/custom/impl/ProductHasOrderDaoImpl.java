package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.ProductHasOrderEntity;
import pos.clothify.store.reporsitory.custom.ProductHasOrderDao;

import java.util.List;

public class ProductHasOrderDaoImpl implements ProductHasOrderDao {
    @Override
    public boolean save(ProductHasOrderEntity entity) {
        return false;
    }

    @Override
    public boolean update(ProductHasOrderEntity entity) {
        return false;
    }

    @Override
    public List<ProductHasOrderEntity> findAll() {
        return List.of();
    }
}
