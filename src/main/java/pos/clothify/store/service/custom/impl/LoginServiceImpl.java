package pos.clothify.store.service.custom.impl;

import javafx.scene.control.Alert;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.model.Login;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.UserDao;
import pos.clothify.store.service.custom.LoginService;
import pos.clothify.store.util.DaoType;

public class LoginServiceImpl implements LoginService {
    @Override
    public String login(Login login) {
        UserDao dao = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = dao.login(login.getUsername(),login.getPassword());
        if(userEntity==null){
            new Alert(Alert.AlertType.ERROR, "User can not found").show();

        }else if(userEntity.getJobRoleEntity().getJobRoleName().equals("Admin")){
            return "Admin Found";
        }else if(userEntity.getJobRoleEntity().getJobRoleName().equals("User")){
            return "User Found";
        }
       return "User Can not Found";
    }
}
