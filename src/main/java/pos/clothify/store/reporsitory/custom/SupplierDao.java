package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

public interface SupplierDao extends CrudReporsitory<SupplierEntity> {
    Integer findByMaxId();

    SupplierEntity findBySupplierEmail(String email);

    SupplierEntity getSupplierByName(String name);
}
