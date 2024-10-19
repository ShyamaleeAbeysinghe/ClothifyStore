package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.reporsitory.custom.ProductDao;
import pos.clothify.store.service.custom.ProductService;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<ProductEntity> findAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<ProductEntity> query = session.getCriteriaBuilder().createQuery(ProductEntity.class);
        Root<ProductEntity> productEntityRoot = query.from(ProductEntity.class);
        query.select(productEntityRoot);
        query.where(session.getCriteriaBuilder().equal(productEntityRoot.get("Status"),1));

        List<ProductEntity> resultList = session.createQuery(query).getResultList();
        session.close();

        return resultList;
    }

    @Override
    public ProductEntity findByProductName(String name) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<ProductEntity> query = session.getCriteriaBuilder().createQuery(ProductEntity.class);
        Root<ProductEntity> productEntityRoot = query.from(ProductEntity.class);
        query.select(productEntityRoot);
        query.where(session.getCriteriaBuilder().equal(productEntityRoot.get("ProductName"),name));

        return session.createQuery(query).getSingleResultOrNull();
    }
    @Override
    public Integer findByMaxId() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery(Object.class);
        Root<ProductEntity> productEntityRoot = query.from(ProductEntity.class);
        query.select(session.getCriteriaBuilder().max((Expression)productEntityRoot.get("idProduct")));

        return (Integer) session.createQuery(query).getSingleResultOrNull();
    }
}
