package pos.clothify.store.service.custom;

import pos.clothify.store.model.User;
import pos.clothify.store.service.SuperService;

public interface UserService extends SuperService {
    boolean addUser(User user);
}
