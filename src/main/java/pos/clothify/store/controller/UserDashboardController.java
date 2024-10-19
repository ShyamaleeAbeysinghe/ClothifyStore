package pos.clothify.store.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardController {

    private String email;
    public UserDashboardController(String email){
        this.email=email;
    }


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

    public void btnOnActionAddOrder(ActionEvent actionEvent) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddOrderFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void btnOnActionViewOrder(ActionEvent actionEvent) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ViewOrderFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void btnOnActionUserProfile(ActionEvent actionEvent) {

        Stage stage=new Stage();

        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/view/ProfileFormController.fxml"));
            load.setController(new ProfileFormController(email));
            Parent parent = load.load();
            stage.setScene(new Scene(parent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}

