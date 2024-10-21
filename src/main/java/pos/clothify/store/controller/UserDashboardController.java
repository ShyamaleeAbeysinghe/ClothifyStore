package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
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
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton btnPriduct;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnViewOrder;


    @FXML
    void btnOnActionEmployeeRegistration(ActionEvent event) {
        Stage curruntStage=(Stage) btnEmployee.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/EmployeeFormController.fxml"));
            loads.setController(new EmployeeFormController("User",email));
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();


    }


    @FXML
    void btnOnActionProductCatalog(ActionEvent event) {
        Stage currentStage=(Stage) btnPriduct.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/ProductCatalogFormController.fxml"));
            loads.setController(new ProductCatalogFormController("User",email));
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        currentStage.close();



    }

    @FXML
    void btnOnActionSuplierRegistration(ActionEvent event) {
        Stage currentStage=(Stage) btnSupplier.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/SuplierFormController.fxml"));
            loads.setController(new SuplierFormController("User",email));
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        currentStage.close();


    }

    public void btnOnActionAddOrder(ActionEvent actionEvent) {
        Stage curruntStage=(Stage) btnOrder.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/view/AddOrderFormController.fxml"));
            load.setController(new AddOrderFormController(email));
            Parent parent = load.load();
            stageNew.setScene(new Scene(parent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();
    }

    public void btnOnActionViewOrder(ActionEvent actionEvent) {
        Stage curruntStage=(Stage) btnViewOrder.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/view/ViewOrderFormController.fxml"));
            load.setController(new ViewOrderFormController(email));
            Parent parent = load.load();
            stageNew.setScene(new Scene(parent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();
    }

    public void btnOnActionUserProfile(ActionEvent actionEvent) {
        Stage curruntStage=(Stage) btnUser.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/view/ProfileFormController.fxml"));
            load.setController(new ProfileFormController(email));
            Parent parent = load.load();
            stageNew.setScene(new Scene(parent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();
    }
}

