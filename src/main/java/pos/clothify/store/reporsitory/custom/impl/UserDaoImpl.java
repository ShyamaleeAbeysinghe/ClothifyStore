package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.reporsitory.custom.UserDao;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
        return false;
    }

    @Override
    public boolean update(UserEntity entity) {
        return false;
    }

    @Override
    public List<UserEntity> findAll() {
        return List.of();
    }
}
