package pos.clothify.store.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pos.clothify.store.model.Login;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.LoginService;
import pos.clothify.store.service.custom.UserService;
import pos.clothify.store.util.ServiceType;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnOnActionLogin(ActionEvent event) {
        LoginService service = SuperFactory.getInstance().getServiceType(ServiceType.LOGIN);


        if(txtUsername==null || txtPassword==null){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else{
            Login login=new Login(
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            String loginResponse = service.login(login);
            if(loginResponse.equals("User can not found")){
                new Alert(Alert.AlertType.INFORMATION, "Invaid Credintions").show();

            }else if (loginResponse.equals("Admin Found")){
                Stage stage=new Stage();

                try {
                    System.out.println("hereeeeeeeee");
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardFormController.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();

            }else if (loginResponse.equals("User Found")){
                Stage stage=new Stage();

                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboardFormController.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            }
        }



    }

}

