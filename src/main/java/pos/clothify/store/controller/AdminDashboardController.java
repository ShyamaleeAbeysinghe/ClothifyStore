package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {
    @FXML
    private JFXButton btnReturn;


    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnPriduct;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnUser;


    @FXML
    void btnOnActionEmployeeRegistration(ActionEvent event) {
        Stage currentStage=(Stage) btnEmployee.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/EmployeeFormController.fxml"));
            loads.setController(new EmployeeFormController("Admin",""));
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        currentStage.close();

    }

    @FXML
    void btnOnActionProductCatalog(ActionEvent event) {
        Stage currentStage=(Stage) btnPriduct.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/ProductCatalogFormController.fxml"));
            loads.setController(new ProductCatalogFormController("Admin",""));
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
        Stage currentStage=(Stage) btnRegister.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/SuplierFormController.fxml"));
            loads.setController(new SuplierFormController("Admin",""));
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        currentStage.close();

    }

    @FXML
    void btnOnActionUserReristration(ActionEvent event) {
        Stage currentStage=(Stage) btnUser.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            stageNew.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        currentStage.close();

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
    void btnOnActionReturn(ActionEvent event) {
        Stage curruntStage=(Stage) btnReturn.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/ReturnItemFormController.fxml"));
            loads.setController(new ReturnItemFormController());
            Parent load = loads.load();
            stageNew.setScene(new Scene(load));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();
    }


}

