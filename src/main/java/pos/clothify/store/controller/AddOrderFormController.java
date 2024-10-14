package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddOrderFormController {

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<?> tblAddOrder;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private JFXTextField txtSize;

    @FXML
    void btnOnActionAdd(ActionEvent event) {

    }

    @FXML
    void btnOnActionReload(ActionEvent event) {

    }

}

