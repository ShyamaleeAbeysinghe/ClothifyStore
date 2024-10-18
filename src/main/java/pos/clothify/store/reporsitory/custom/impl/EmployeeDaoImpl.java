package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import pos.clothify.store.entity.EmployeeEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.reporsitory.custom.EmployeeDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        return false;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return List.of();
    }

    @Override
    public Integer findByMaxId() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery(Object.class);
        Root<EmployeeEntity> employeeEntityRoot = query.from(EmployeeEntity.class);
        query.select(session.getCriteriaBuilder().max((Expression)employeeEntityRoot.get("idEmployee")));

        return (Integer) session.createQuery(query).getSingleResultOrNull();
    }

    @Override
    public EmployeeEntity findBySupplierEmail(String email) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<EmployeeEntity> query = session.getCriteriaBuilder().createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> employeeEntityRoot = query.from(EmployeeEntity.class);
        query.select(employeeEntityRoot);
        query.where(session.getCriteriaBuilder().equal(employeeEntityRoot.get("Email"),email));

        return session.createQuery(query).getSingleResultOrNull();
    }
}
