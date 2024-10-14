package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    }

    @FXML
    void btnOnActionReturn(ActionEvent event) {

    }

    @FXML
    void btnOnActionSearch(ActionEvent event) {

    }

}

