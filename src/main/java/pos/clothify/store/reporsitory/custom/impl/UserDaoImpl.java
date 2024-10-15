package pos.clothify.store.reporsitory.custom.impl;

import org.hibernate.Session;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.reporsitory.custom.UserDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
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
