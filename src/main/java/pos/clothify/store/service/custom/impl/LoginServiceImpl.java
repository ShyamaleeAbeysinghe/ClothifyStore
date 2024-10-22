package pos.clothify.store.service.custom.impl;

import javafx.scene.control.Alert;
import pos.clothify.store.config.EncryptionConfig;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.model.Login;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.custom.UserDao;
import pos.clothify.store.service.custom.LoginService;
import pos.clothify.store.util.DaoType;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class LoginServiceImpl implements LoginService {
    @Override
    public String login(Login login) {
        UserDao dao = DaoFactory.getInstance().getDao(DaoType.USER);

        List<UserEntity> userList = dao.findAll();
        AtomicReference<UserEntity> userEntity= new AtomicReference<>();
        userList.forEach(user -> {
            try {
            EncryptionConfig encryptionConfig = EncryptionConfig.getInstance();
                String decryptedpw = encryptionConfig.decrypt(user.getPassword(), encryptionConfig.getKey());

                if (user.getUserEmail().equals(login.getUsername()) && decryptedpw.equals(login.getPassword())){
                    userEntity.set(user);
                }
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        if(userEntity.get()==null){
            new Alert(Alert.AlertType.ERROR, "User can not found").show();

        }else if(userEntity.get().getJobRoleEntity().getJobRoleName().equals("Admin")){
            return "Admin Found";
        }else if(userEntity.get().getJobRoleEntity().getJobRoleName().equals("User")){
            return "User Found";
        }
       return "User Can not Found";
    }

    @Override
    public void load() {
        UserDao dao = DaoFactory.getInstance().getDao(DaoType.USER);
        dao.load();
    }
}
