package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.EmployeeEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

public interface EmployeeDao extends CrudReporsitory<EmployeeEntity> {
    Integer findByMaxId();

    EmployeeEntity findByEmployeeEmail(String email);
}
