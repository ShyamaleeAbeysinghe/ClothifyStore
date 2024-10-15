package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.ReturnEntity;
import pos.clothify.store.reporsitory.custom.ReturnDao;

import java.util.List;

public class ReturnDaoImpl implements ReturnDao {
    @Override
    public boolean save(ReturnEntity entity) {
        return false;
    }

    @Override
    public boolean update(ReturnEntity entity) {
        return false;
    }

    @Override
    public List<ReturnEntity> findAll() {
        return List.of();
    }
}
