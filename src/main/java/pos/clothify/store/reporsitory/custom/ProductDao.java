package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

public interface ProductDao extends CrudReporsitory<ProductEntity> {
    ProductEntity findByProductName(String name);
    Integer findByMaxId();
}
