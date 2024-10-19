package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import pos.clothify.store.model.User;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.UserService;
import pos.clothify.store.util.ServiceType;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileFormController implements Initializable {
    private String email;
    private  User user;
    private UserService service;
    public ProfileFormController(String email){
        this.email=email;
    }


    @FXML
    public PasswordField txtConformPassword;

    @FXML
    public PasswordField txtNewPassword;

    @FXML
    public PasswordField txtCurrentPassword;

    @FXML
    public CheckBox checkPasswordChange;

    @FXML
    public JFXButton btnUpdatePassword;

    @FXML
    private ComboBox combRole;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         service = SuperFactory.getInstance().getServiceType(ServiceType.USER);
        user = service.findByEmail(email);

        txtUserId.setText(user.getUserId());
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        txtEmail.setText(user.getEmail());
        txtAddress.setText(user.getAddress());
        txtContact.setText(user.getContact());
        txtNIC.setText(user.getNic());
        combRole.setValue(user.getRole());
        clearPasswords();
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {



        User userProfile=new User(
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

        if(service.updateUser(userProfile)) {
            new Alert(Alert.AlertType.INFORMATION, "update succses").show();
        }
    }

    @FXML
    void btnOnActionUpdatePassword(ActionEvent event) {
        if(user.getPassword().equals(txtCurrentPassword.getText())){
            if(!validatePassword(txtNewPassword.getText())){
                new Alert(Alert.AlertType.ERROR, "invalid password").show();
            }else {
                if(txtNewPassword.getText().equals(txtConformPassword.getText())){
                    user.setPassword(txtNewPassword.getText());
                    System.out.println(user);
                    service.updateUser(user);
                    clearPasswords();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Confirm password did not match").show();
                }
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Current password is incorrect").show();
        }

    }

    private void clearPasswords() {
        txtCurrentPassword.setEditable(false);
        txtNewPassword.setEditable(false);
        txtConformPassword.setEditable(false);
        btnUpdatePassword.setDisable(true);

        txtCurrentPassword.setText("");
        txtNewPassword.setText("");
        txtConformPassword.setText("");

        checkPasswordChange.setSelected(false);
    }

    public void checkPasswordSelection(ActionEvent actionEvent) {
        if(checkPasswordChange.isSelected()){
            txtCurrentPassword.setEditable(true);
            txtNewPassword.setEditable(true);
            txtConformPassword.setEditable(true);
            btnUpdatePassword.setDisable(false);
        }else {
            clearPasswords();

        }
    }

    public boolean validatePassword(String password) {
//        final Pattern VALID_EMAIL_ADDRESS_REGEX =
//                Pattern.compile( "^(?=.[a-zA-Z])(?=.\\d)(?=.[!@#$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$", Pattern.CASE_INSENSITIVE);
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";

//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(password);
        boolean isValid = Pattern.matches(regex, password);
        return isValid;
    }


}

