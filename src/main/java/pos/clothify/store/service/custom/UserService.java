package pos.clothify.store.service.custom;

import javafx.collections.ObservableList;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.model.Login;
import pos.clothify.store.model.User;
import pos.clothify.store.service.SuperService;

import java.util.List;

public interface UserService extends SuperService {
    boolean addUser(User user);

    ObservableList<String> getJobRoleName();

    Integer findMaxId();

    ObservableList<User> getAllUsers();

    User findByEmail(String email);

    boolean updateUser(User user);

    boolean deleteUser(String email);


}
