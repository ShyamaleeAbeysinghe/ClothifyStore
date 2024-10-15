package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.model.User;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.custom.JobRoleDao;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.UserService;
import pos.clothify.store.util.DaoType;
import pos.clothify.store.util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    @FXML
    public TableColumn colRole;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colLastName1;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableView<?> tblUser;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    public ComboBox combRole;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> jobRoleNameList= FXCollections.observableArrayList();
        JobRoleDao jobRoleDao =DaoFactory.getInstance().getDao(DaoType.JOBROLE);
        List<JobRoleEntity> all = jobRoleDao.findAll();

        all.forEach(jobRoleEntity -> {
            jobRoleNameList.add(jobRoleEntity.getJobRoleName());
        });

        combRole.setItems(jobRoleNameList);
    }



    @FXML
    void btnOnActionAdd(ActionEvent event) {
        UserService userService= SuperFactory.getInstance().getServiceType(ServiceType.USER);

        if(txtUserId==null || txtFirstName==null || txtLastName==null || txtAddress==null || txtNIC==null || txtContact==null || txtEmail==null || combRole==null){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else {
            User user=new User(
                    txtUserId.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtAddress.getText(),
                    txtNIC.getText(),
                    txtContact.getText(),
                    txtEmail.getText(),
                    (String) combRole.getValue()
            );

            if(userService.addUser(user)){
                new Alert(Alert.AlertType.INFORMATION).show();
            }else{
                new Alert(Alert.AlertType.ERROR).show();
            }

            System.out.println(user);
        }



    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {






    }

    @FXML
    void btnOnActionReload(ActionEvent event) {

    }

    @FXML
    void btnOnActionSearch(ActionEvent event) {

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {

    }


}

