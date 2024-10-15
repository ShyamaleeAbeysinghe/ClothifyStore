package pos.clothify.store.reporsitory.custom.impl;

import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.reporsitory.custom.JobRoleDao;

import java.util.List;

public class JobRoleDaoImpl implements JobRoleDao {


    @Override
    public boolean save(JobRoleEntity entity) {
        return false;
    }

    @Override
    public boolean update(JobRoleEntity entity) {
        return false;
    }

    @Override
    public List<JobRoleEntity> findAll() {
        return List.of();
    }
}
