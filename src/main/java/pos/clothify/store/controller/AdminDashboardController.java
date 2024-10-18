package pos.clothify.store.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    void btnOnActionEmployeeRegistration(ActionEvent event) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/EmployeeFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnOnActionProductCatalog(ActionEvent event) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ProductCatalogFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnOnActionSuplierRegistration(ActionEvent event) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SuplierFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnOnActionUserReristration(ActionEvent event) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

}

