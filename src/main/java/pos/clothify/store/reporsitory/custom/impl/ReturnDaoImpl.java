package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import pos.clothify.store.entity.ReturnEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.reporsitory.custom.ReturnDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class ReturnDaoImpl implements ReturnDao {
    @Override
    public boolean save(ReturnEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ReturnEntity entity) {
        return false;
    }

    @Override
    public List<ReturnEntity> findAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<ReturnEntity> query = session.getCriteriaBuilder().createQuery(ReturnEntity.class);
        Root<ReturnEntity> returnEntityRoot = query.from(ReturnEntity.class);
        query.select(returnEntityRoot);
        query.where(session.getCriteriaBuilder().equal(returnEntityRoot.get("Status"),1));

        List<ReturnEntity> resultList = session.createQuery(query).getResultList();
        session.close();

        return resultList;
    }
}
