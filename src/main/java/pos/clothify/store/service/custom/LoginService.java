package pos.clothify.store.service.custom;

import pos.clothify.store.model.Login;
import pos.clothify.store.service.SuperService;

public interface LoginService extends SuperService {
    String login(Login login);
    void load();
}
