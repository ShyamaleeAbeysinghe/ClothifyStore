package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pos.clothify.store.entity.JobRoleEntity;
import pos.clothify.store.model.User;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.JobRoleDao;
import pos.clothify.store.reporsitory.custom.UserDao;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.UserService;
import pos.clothify.store.service.custom.impl.UserSeviceImpl;
import pos.clothify.store.util.DaoType;
import pos.clothify.store.util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFormController implements Initializable {

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnBack;

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
    private TableView<User> tblUser;

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

        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadTable();

        UserService service = SuperFactory.getInstance().getServiceType(ServiceType.USER);

        combRole.setItems(service.getJobRoleName());

        Integer maxId = service.findMaxId();
       if(maxId==null){
           maxId=0;
       }
        maxId++;
        txtUserId.setText(Integer.toString(maxId));

        tblUser.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            addValueToText(newVal);
        });


    }

    private void addValueToText(User newVal) {
        txtUserId.setText(newVal.getUserId());
        txtFirstName.setText(newVal.getFirstName());
        txtLastName.setText(newVal.getLastName());
        txtAddress.setText(newVal.getAddress());
        txtNIC.setText(newVal.getNic());
        txtContact.setText(newVal.getContact());
        txtEmail.setText(newVal.getEmail());
        combRole.setValue(newVal.getRole());

    }

    private void loadTable() {
        UserService service = SuperFactory.getInstance().getServiceType(ServiceType.USER);
        tblUser.setItems(service.getAllUsers());
    }

    @FXML
    void btnOnActionBack(ActionEvent event) {
        Stage curruntStage=(Stage) btnBack.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/AdminDashboardFormController.fxml"));
            loads.setController(new AdminDashboardController());
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();
    }

    @FXML
    void btnOnActionLogOut(ActionEvent event) {
        Stage curruntStage=(Stage) btnLogOut.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/LoginFormController.fxml"));
            loads.setController(new LoginFormController());
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        UserService service= SuperFactory.getInstance().getServiceType(ServiceType.USER);



        User user=new User(
                txtUserId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtAddress.getText(),
                txtNIC.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                (String) combRole.getValue(),
                null
        );

        if(service.addUser(user)){
            clear();
            loadTable();
            Integer maxId = service.findMaxId();
            maxId++;
            txtUserId.setText(Integer.toString(maxId));

        }
        System.out.println(user);



    }

    private void clear() {
        txtUserId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtNIC.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        combRole.setValue("");
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        UserService service= SuperFactory.getInstance().getServiceType(ServiceType.USER);
        if(service.deleteUser(txtEmail.getText())){
            clear();
            loadTable();
        }
    }

    @FXML
    void btnOnActionReload(ActionEvent event) {
        loadTable();

    }

    @FXML
    void btnOnActionSearch(ActionEvent event) {
        UserService service= SuperFactory.getInstance().getServiceType(ServiceType.USER);
        User user = service.findByEmail(txtEmail.getText());

        if(user!=null){
            txtUserId.setText(user.getUserId());
            txtFirstName.setText(user.getFirstName());
            txtLastName.setText(user.getLastName());
            txtAddress.setText(user.getAddress());
            txtNIC.setText(user.getNic());
            txtContact.setText(user.getContact());
            combRole.setValue(user.getRole()

            );


        }



    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        UserService service= SuperFactory.getInstance().getServiceType(ServiceType.USER);



        User user=new User(
                txtUserId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtAddress.getText(),
                txtNIC.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                (String) combRole.getValue(),
                null
        );

        if(service.updateUser(user)){
            clear();
            loadTable();
            Integer maxId = service.findMaxId();
            maxId++;
            txtUserId.setText(Integer.toString(maxId));
        }

    }


}

