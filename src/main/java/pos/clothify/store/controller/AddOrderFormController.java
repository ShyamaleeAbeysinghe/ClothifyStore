package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pos.clothify.store.model.AddOrder;

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

        if(txtProductName==null || txtSize==null || txtQTY==null){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else{
            AddOrder addOrder=new AddOrder(
                    txtProductName.getText(),
                    txtSize.getText(),
                    Integer.parseInt(txtQTY.getText())

            );
        }



    }

    @FXML
    void btnOnActionReload(ActionEvent event) {

    }

}

