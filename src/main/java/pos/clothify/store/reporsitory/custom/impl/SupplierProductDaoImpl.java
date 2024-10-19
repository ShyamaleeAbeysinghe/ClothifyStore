package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.entity.SupplierProductEntity;
import pos.clothify.store.reporsitory.custom.SupplierProductDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class SupplierProductDaoImpl implements SupplierProductDao {
    @Override
    public boolean save(SupplierProductEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(SupplierProductEntity entity) {
        return false;
    }

    @Override
    public List<SupplierProductEntity> findAll() {
        return List.of();
    }


    @Override
    public SupplierProductEntity findByProduct(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<SupplierProductEntity> query = session.getCriteriaBuilder().createQuery(SupplierProductEntity.class);
        Root<SupplierProductEntity> supplierProductEntityRoot = query.from(SupplierProductEntity.class);
        query.select(supplierProductEntityRoot);
        query.where(session.getCriteriaBuilder().equal(supplierProductEntityRoot.get("product"),productEntity));
        System.out.println(productEntity.getIdProduct());

        return session.createQuery(query).getSingleResultOrNull();
    }
}
