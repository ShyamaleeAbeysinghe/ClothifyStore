package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.reporsitory.custom.ProductDao;
import pos.clothify.store.service.custom.ProductService;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {
        return false;
    }

    @Override
    public boolean update(ProductEntity entity) {
        return false;
    }

    @Override
    public List<ProductEntity> findAll() {
        return List.of();
    }
}
