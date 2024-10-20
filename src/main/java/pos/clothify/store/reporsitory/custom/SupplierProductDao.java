package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.entity.SupplierProductEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

public interface SupplierProductDao extends CrudReporsitory<SupplierProductEntity> {
SupplierProductEntity findByProduct(ProductEntity productEntity);
}
