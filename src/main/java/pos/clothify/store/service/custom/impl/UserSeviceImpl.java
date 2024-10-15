package pos.clothify.store.service.custom.impl;

import org.modelmapper.ModelMapper;
import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.model.User;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.JobRoleDao;
import pos.clothify.store.reporsitory.custom.UserDao;
import pos.clothify.store.service.custom.UserService;
import pos.clothify.store.util.DaoType;

public class UserSeviceImpl implements UserService {


    @Override
    public boolean addUser(User user) {
        JobRoleDao jobRoleDao = DaoFactory.getInstance().getDao(DaoType.JOBROLE);
        JobRoleEntity jobRoleEntity = jobRoleDao.getjobRoleByName(user.getRole());

        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
        userEntity.setStatus(1);
        userEntity.setJobRoleEntity(jobRoleEntity);

        System.out.println(userEntity);

        UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);


        return userDao.save(userEntity);
    }
}
