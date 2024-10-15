package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pos.clothify.store.model.User;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.custom.JobRoleDao;
import pos.clothify.store.util.DaoType;

public class UserFormController {
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



    @FXML
    void btnOnActionAdd(ActionEvent event) {

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
        }



    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        JobRoleDao jobRoleDao =DaoFactory.getInstance().getDao(DaoType.JOBROLE);
        jobRoleDao.findAll();

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

