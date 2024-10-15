package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.SupplierProductEntity;
import pos.clothify.store.reporsitory.custom.SupplierProductDao;

import java.util.List;

public class SupplierProductDaoImpl implements SupplierProductDao {
    @Override
    public boolean save(SupplierProductEntity entity) {
        return false;
    }

    @Override
    public boolean update(SupplierProductEntity entity) {
        return false;
    }

    @Override
    public List<SupplierProductEntity> findAll() {
        return List.of();
    }
}
