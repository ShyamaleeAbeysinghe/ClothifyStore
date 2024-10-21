package pos.clothify.store.reporsitory.custom.impl;

import org.hibernate.Session;
import pos.clothify.store.entity.ProductHasOrderEntity;
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
}
