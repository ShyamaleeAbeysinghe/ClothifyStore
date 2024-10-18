package pos.clothify.store.reporsitory.custom;

import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.reporsitory.CrudReporsitory;

public interface UserDao extends CrudReporsitory<UserEntity> {
    UserEntity findByUserEmail(String email);

    Integer findBymaxId();

    UserEntity login(String userEmail,String password);


}
