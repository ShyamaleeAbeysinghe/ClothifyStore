package pos.clothify.store.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import pos.clothify.store.model.Login;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.LoginService;
import pos.clothify.store.service.custom.UserService;
import pos.clothify.store.util.ServiceType;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {



    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private CheckBox showPassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginService service = SuperFactory.getInstance().getServiceType(ServiceType.LOGIN);
        service.load();
    }

    @FXML
    void btnOnActionLogin(ActionEvent event) {
        LoginService service = SuperFactory.getInstance().getServiceType(ServiceType.LOGIN);


        if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else{
            Login login=new Login(
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            String loginResponse = service.login(login);

            Stage curruntStage=(Stage) txtUsername.getScene().getWindow();
            Stage newstage=new Stage();
            if(loginResponse.equals("User can not found")){
                new Alert(Alert.AlertType.INFORMATION, "Invaid Credintions").show();

            }else if (loginResponse.equals("Admin Found")){


                try {
                    FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/AdminDashboardFormController.fxml"));
                    loads.setController(new AdminDashboardController());
                    Parent load = loads.load();
                    newstage.setScene(new Scene(load));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                newstage.show();
                curruntStage.close();

            }else if (loginResponse.equals("User Found")){


                try {
                    FXMLLoader load = new FXMLLoader(getClass().getResource("/view/UserDashboardFormController.fxml"));
                    load.setController(new UserDashboardController(txtUsername.getText()));
                    Parent parent = load.load();
                    newstage.setScene(new Scene(parent));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                newstage.show();
                curruntStage.close();
            }
        }



    }


    public void showPassword(javafx.scene.input.MouseEvent mouseEvent) {
        String password = txtPassword.getText();
        txtPassword.setText("");
        txtPassword.setPromptText(password);
        showPassword.setSelected(true);
    }

    public void hidePassword(javafx.scene.input.MouseEvent mouseEvent) {
        String password = txtPassword.getPromptText();
        txtPassword.setText(password);
        txtPassword.setPromptText("Password");
    }
}

