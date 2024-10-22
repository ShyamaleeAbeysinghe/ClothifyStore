package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ProductHasOrderEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.reporsitory.custom.ProductHasOrderDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class ProductHasOrderDaoImpl implements ProductHasOrderDao {
    @Override
    public boolean save(ProductHasOrderEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ProductHasOrderEntity entity) {
        return false;
    }

    @Override
    public List<ProductHasOrderEntity> findAll() {
        return List.of();
    }

    @Override
    public List<ProductHasOrderEntity> findByOrderId(OrderEntity orderEntity) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<ProductHasOrderEntity> query = session.getCriteriaBuilder().createQuery(ProductHasOrderEntity.class);
        Root<ProductHasOrderEntity> productHasOrderEntityRoot = query.from(ProductHasOrderEntity.class);
        query.select(productHasOrderEntityRoot);
        query.where(session.getCriteriaBuilder().equal(productHasOrderEntityRoot.get("order"),orderEntity));

        List<ProductHasOrderEntity> resultList = session.createQuery(query).getResultList();
        session.close();

        return resultList;
    }
}
