package pos.clothify.store.reporsitory.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.reporsitory.custom.JobRoleDao;
import pos.clothify.store.util.HibernateUtil;

import java.util.List;

public class JobRoleDaoImpl implements JobRoleDao {


    @Override
    public boolean save(JobRoleEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(JobRoleEntity entity) {
        return false;
    }

    @Override
    public List<JobRoleEntity> findAll() {

        Session session = HibernateUtil.getSession();
        CriteriaQuery<JobRoleEntity> query = session.getCriteriaBuilder().createQuery(JobRoleEntity.class);
        query.from(JobRoleEntity.class);

        List<JobRoleEntity> resultList = session.createQuery(query).getResultList();
        session.close();
        return resultList;


    }

    @Override
    public JobRoleEntity getjobRoleByName(String name) {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<JobRoleEntity> query = session.getCriteriaBuilder().createQuery(JobRoleEntity.class);
        Root<JobRoleEntity> roleEntityRoot = query.from(JobRoleEntity.class);
        query.select(roleEntityRoot);
        query.where(session.getCriteriaBuilder().equal(roleEntityRoot.get("JobRoleName"),name));

        return session.createQuery(query).getSingleResult();
    }
}
