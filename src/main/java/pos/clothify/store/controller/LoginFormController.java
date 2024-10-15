package pos.clothify.store.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import pos.clothify.store.model.Login;

public class LoginFormController {

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnOnActionLogin(ActionEvent event) {

        if(txtUsername==null || txtPassword==null){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else{
            Login login=new Login(
                    txtUsername.getText(),
                    txtPassword.getText()
            );
        }



    }

}

