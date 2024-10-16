package pos.clothify.store.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSeviceImpl implements UserService {


    @Override
    public boolean addUser(User user) {

        if (user.getUserId().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getAddress().isEmpty() || user.getNic().isEmpty() || user.getContact().isEmpty() || user.getEmail().isEmpty() || user.getRole().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Some Fields are empty").show();

            return false;

        } else if (!validateEmail(user.getEmail())) {
            new Alert(Alert.AlertType.ERROR, "Incorrect email").show();
            return false;

        } else if (!validateNic(user.getNic())) {
            new Alert(Alert.AlertType.ERROR, "Incorrect NIC").show();
            return false;

        } else if (!validateCotact(user.getContact())) {
            new Alert(Alert.AlertType.ERROR, "Incorrect Phone Number").show();
            return false;
        } else {
            UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
            UserEntity userEmail = userDao.findByUserEmail(user.getEmail());

            if (userEmail == null) {
                JobRoleDao jobRoleDao = DaoFactory.getInstance().getDao(DaoType.JOBROLE);
                JobRoleEntity jobRoleEntity = jobRoleDao.getjobRoleByName(user.getRole());

                UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
                userEntity.setStatus(1);
                userEntity.setPassword("1234@abcd");
                userEntity.setJobRoleEntity(jobRoleEntity);

                System.out.println(userEntity);

                boolean save = userDao.save(userEntity);
                if (save == true) {
                    new Alert(Alert.AlertType.INFORMATION).show();
                    return true;

                } else {
                    new Alert(Alert.AlertType.ERROR).show();
                    return false;

                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Email already exist").show();
                return false;

            }
        }

    }

    @Override
    public ObservableList<String> getJobRoleName() {
        ObservableList<String> jobRoleNameList = FXCollections.observableArrayList();
        JobRoleDao jobRoleDao = DaoFactory.getInstance().getDao(DaoType.JOBROLE);
        List<JobRoleEntity> all = jobRoleDao.findAll();

        all.forEach(jobRoleEntity -> {
            jobRoleNameList.add(jobRoleEntity.getJobRoleName());
        });
        return jobRoleNameList;
    }

    @Override
    public Integer findMaxId() {
        UserDao dao = DaoFactory.getInstance().getDao(DaoType.USER);
        return dao.findBymaxId();

    }

    @Override
    public ObservableList<User> getAllUsers() {
        ObservableList<User> userList=FXCollections.observableArrayList();
        UserDao dao = DaoFactory.getInstance().getDao(DaoType.USER);
        List<UserEntity> all = dao.findAll();
        all.forEach(userEntity -> {
            User user = new ModelMapper().map(userEntity, User.class);
             userList.add(user);
        });
        return userList;
    }

    @Override
    public User findByEmail(String email) {
        UserDao dao = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = dao.findByUserEmail(email);
        if(userEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found User").show();
            return null;
        }
        return new ModelMapper().map(userEntity, User.class);


    }


    public boolean validateEmail(String emailStr) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public boolean validateNic(String nic) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[0-9]{9}[VvXx]$|[0-9]{12,12}$", Pattern.CASE_INSENSITIVE);


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(nic);
        return matcher.matches();
    }

    public boolean validateCotact(String contact) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[0-9]{10,10}$", Pattern.CASE_INSENSITIVE);


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(contact);
        return matcher.matches();
    }


}
