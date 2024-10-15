package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pos.clothify.store.model.ViewOrder;

public class ViewOrderFormController {

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableView<?> tblViewOder;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    void btnOnActionReload(ActionEvent event) {
        if(txtOrderId==null || txtCustomerName==null || txtTotal==null){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();

        }else{
            ViewOrder viewOrder=new ViewOrder(
                    txtOrderId.getText(),
                    txtCustomerName.getText(),
                    Double.parseDouble(txtTotal.getText())
            );
        }


    }

    @FXML
    void btnOnActionReturn(ActionEvent event) {

    }

    @FXML
    void btnOnActionSearch(ActionEvent event) {

    }

}

