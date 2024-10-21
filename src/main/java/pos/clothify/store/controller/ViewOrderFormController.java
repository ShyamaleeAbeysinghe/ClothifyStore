package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pos.clothify.store.model.ViewOrder;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.ViewOrderService;
import pos.clothify.store.util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewOrderFormController implements Initializable {
    private String email;
    public ViewOrderFormController(String email){
        this.email=email;
    }

    @FXML
    private JFXButton btnBack;

    @FXML
    private TableColumn colCustomerName;

    @FXML
    private TableColumn colOrderId;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableView<ViewOrder> tblViewOder;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

       loadTable();
    }
    public void loadTable(){
        ViewOrderService service = SuperFactory.getInstance().getServiceType(ServiceType.VIEWODER);
        ObservableList<ViewOrder> allOrders = service.findAllOrders();
        tblViewOder.setItems(allOrders);
    }

    @FXML
    void btnOnActionReload(ActionEvent event) {
       loadTable();
    }

    @FXML
    void btnOnActionReturn(ActionEvent event) {
        ViewOrderService service = SuperFactory.getInstance().getServiceType(ServiceType.VIEWODER);
        Boolean returnOrder = service.returnOrder(Integer.parseInt(txtOrderId.getText()));

        if(returnOrder){
            txtOrderId.setText("");
            txtCustomerName.setText("");
            txtTotal.setText("");

            loadTable();
            new Alert(Alert.AlertType.ERROR, "Order return success").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Can not update order").show();
        }


    }

    @FXML
    void btnOnActionSearch(ActionEvent event) {
        ViewOrderService service = SuperFactory.getInstance().getServiceType(ServiceType.VIEWODER);
        ViewOrder viewOrder = service.findByOrderId(Integer.parseInt(txtOrderId.getText()));

        if(viewOrder!=null){
            txtCustomerName.setText(viewOrder.getCustomerName());
            txtTotal.setText(Double.toString(viewOrder.getTotal()));
        }else {
            txtCustomerName.setText("");
            txtTotal.setText("");
        }

    }
    @FXML
    void btnOnActionBack(ActionEvent event) {
        Stage curruntStage=(Stage) btnBack.getScene().getWindow();
        Stage stageNew=new Stage();

        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/view/UserDashboardFormController.fxml"));
            load.setController( new UserDashboardController(email));
            Parent parent = load.load();
            stageNew.setScene(new Scene(parent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stageNew.show();
        curruntStage.close();
    }


}

