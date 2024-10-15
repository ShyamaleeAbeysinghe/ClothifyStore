package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.EmployeeEntity;
import pos.clothify.store.reporsitory.custom.EmployeeDao;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        return false;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return List.of();
    }
}
