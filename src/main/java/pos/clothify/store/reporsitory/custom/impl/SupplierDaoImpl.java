package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.reporsitory.custom.SupplierDao;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
        return false;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        return false;
    }

    @Override
    public List<SupplierEntity> findAll() {
        return List.of();
    }
}
