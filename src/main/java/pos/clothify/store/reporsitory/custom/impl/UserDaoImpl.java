package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.reporsitory.custom.UserDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;
import java.util.Objects;

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
        Session session = HibernateUtil.getSession();
        CriteriaQuery<UserEntity> query = session.getCriteriaBuilder().createQuery(UserEntity.class);
        query.from(UserEntity.class);

        List<UserEntity> resultList = session.createQuery(query).getResultList();
        session.close();

        return resultList;
    }

    @Override
    public UserEntity findByUserEmail(String email) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<UserEntity> query = session.getCriteriaBuilder().createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = query.from(UserEntity.class);
        query.select(userEntityRoot);
        query.where(session.getCriteriaBuilder().equal(userEntityRoot.get("UserEmail"),email));

        return session.createQuery(query).getSingleResultOrNull();
    }

    @Override
    public Integer findBymaxId() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery(Object.class);
        Root<UserEntity> userEntityRoot = query.from(UserEntity.class);
        query.select(session.getCriteriaBuilder().max((Expression)userEntityRoot.get("idUser")));

        return (Integer)session.createQuery(query).getSingleResultOrNull();

    }
}
