package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pos.clothify.store.model.ProductHasOrder;
import pos.clothify.store.model.Return;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.ReturnItemService;
import pos.clothify.store.util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnItemFormController implements Initializable {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colProductName;

    @FXML
    private TableColumn colQTY;

    @FXML
    private TableView<ProductHasOrder> tblAddOrder;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXTextField txtDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));


    }

    private void loadTable() {
        ReturnItemService service = SuperFactory.getInstance().getServiceType(ServiceType.RETURN);
        tblAddOrder.setItems(service.orderList(Integer.parseInt(txtOrderId.getText())));
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
    void btnOnActionSearch(ActionEvent event) {
        ReturnItemService service = SuperFactory.getInstance().getServiceType(ServiceType.RETURN);
        Return returnDto = service.findByOrderId(Integer.parseInt(txtOrderId.getText()));
        if(returnDto!=null){
            txtCustomerName.setText(returnDto.getCustomerName());
            txtTotal.setText(Double.toString(returnDto.getTotal()));
            txtDate.setText(returnDto.getDate());
        }
loadTable();
    }


}












