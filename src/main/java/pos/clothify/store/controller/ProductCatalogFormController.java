package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pos.clothify.store.model.Product;

public class ProductCatalogFormController {

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private ComboBox<?> comdSupplierName;

    @FXML
    private TableView<?> tblProduct;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtSize;

    @FXML
    void btnOnActionAdd(ActionEvent event) {

        if(txtProductId==null || txtProductName==null || txtSize==null || txtPrice==null || txtQtyOnHand==null || comdSupplierName==null){

            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else{

            Product product=new Product(
                    txtProductId.getText(),
                    txtProductName.getText(),
                    txtSize.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    (String) comdSupplierName.getValue()
            );
        }
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {

    }

    @FXML
    void btnOnActionReload(ActionEvent event) {

    }

    @FXML
    void btnOnActionSearch(ActionEvent event) {

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {

    }

}

