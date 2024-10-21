package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.reporsitory.custom.OrderDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<OrderEntity> findAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<OrderEntity> query = session.getCriteriaBuilder().createQuery(OrderEntity.class);
        Root<OrderEntity> orderEntityRoot = query.from(OrderEntity.class);
        query.select(orderEntityRoot);
        query.where(session.getCriteriaBuilder().equal(orderEntityRoot.get("Status"),1));

        List<OrderEntity> resultList = session.createQuery(query).getResultList();
        session.close();

        return resultList;
    }

    @Override
    public Integer findByMaxId() {

            Session session = HibernateUtil.getSession();
            CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery(Object.class);
            Root<OrderEntity> orderEntityRoot = query.from(OrderEntity.class);
            query.select(session.getCriteriaBuilder().max(orderEntityRoot.get("idOrder")));

           return (Integer) session.createQuery(query).getSingleResultOrNull();

    }

    @Override
    public OrderEntity findByOrderId(Integer orderId) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<OrderEntity> query = session.getCriteriaBuilder().createQuery(OrderEntity.class);
        Root<OrderEntity> orderEntityRoot = query.from(OrderEntity.class);
        query.select(orderEntityRoot);
        query.where(session.getCriteriaBuilder().equal(orderEntityRoot.get("idOrder"),orderId));

        return session.createQuery(query).getSingleResultOrNull();
    }
}
