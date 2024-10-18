package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.reporsitory.custom.SupplierDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;


    }

    @Override
    public boolean update(SupplierEntity entity) {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public List<SupplierEntity> findAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<SupplierEntity> query = session.getCriteriaBuilder().createQuery(SupplierEntity.class);
        Root<SupplierEntity> supplierEntityRoot = query.from(SupplierEntity.class);
        query.select(supplierEntityRoot);
        query.where(session.getCriteriaBuilder().equal(supplierEntityRoot.get("Status"),1));

        List<SupplierEntity> resultList = session.createQuery(query).getResultList();
        session.close();

        return resultList;

    }

    @Override
    public Integer findByMaxId() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery(Object.class);
        Root<SupplierEntity> supplierEntityRoot = query.from(SupplierEntity.class);
        query.select(session.getCriteriaBuilder().max((Expression)supplierEntityRoot.get("idSupplier")));

        return (Integer) session.createQuery(query).getSingleResultOrNull();
    }

    @Override
    public SupplierEntity findBySupplierEmail(String email) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<SupplierEntity> query = session.getCriteriaBuilder().createQuery(SupplierEntity.class);
        Root<SupplierEntity> supplierEntityRoot = query.from(SupplierEntity.class);
        query.select(supplierEntityRoot);
        query.where(session.getCriteriaBuilder().equal(supplierEntityRoot.get("Email"),email));

        return session.createQuery(query).getSingleResultOrNull();
    }
}
