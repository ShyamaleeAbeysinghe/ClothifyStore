package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pos.clothify.store.model.Product;
import pos.clothify.store.model.User;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.ProductService;
import pos.clothify.store.service.custom.UserService;
import pos.clothify.store.util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductCatalogFormController implements Initializable {

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colProductId;

    @FXML
    private TableColumn colProductName;

    @FXML
    private TableColumn colQtyOnHand;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableColumn colSupplierName;

    @FXML
    private ComboBox comdSupplierName;

    @FXML
    private TableView<Product> tblProduct;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        loadTable();

        ProductService service = SuperFactory.getInstance().getServiceType(ServiceType.PRODUCT);

        comdSupplierName.setItems(service.getSupplierName());

        Integer maxId = service.findByMaxId();

        if(maxId==null){
            maxId=0;
        }
        maxId++;
        txtProductId.setText(Integer.toString(maxId));

    }

    private void loadTable() {
        ProductService service = SuperFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        tblProduct.setItems(service.getAllProducts());
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        ProductService service = SuperFactory.getInstance().getServiceType(ServiceType.PRODUCT);


            Product product=new Product(
                    txtProductId.getText(),
                    txtProductName.getText(),
                    txtSize.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    (String) comdSupplierName.getValue()
            );

            if(service.addProduct(product)){
                clear();
                loadTable();

                Integer maxId = service.findByMaxId();

                if(maxId==null){
                    maxId=0;
                }
                maxId++;
                txtProductId.setText(Integer.toString(maxId));
            }

    }

    private void clear() {
        txtProductId.setText("");
        txtProductName.setText("");
        txtSize.setText("");
        txtPrice.setText("");
        txtQtyOnHand.setText("");
        comdSupplierName.setValue("");

    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        ProductService service = SuperFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        if (service.deleteProduct(txtProductName.getText())){
            clear();
            loadTable();
        }
    }

    @FXML
    void btnOnActionReload(ActionEvent event) {
        loadTable();

    }

    @FXML
    void btnOnActionSearch(ActionEvent event) {
        ProductService service = SuperFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        Product product = service.findProductName(txtProductName.getText());

        if(product!=null){
            txtProductId.setText(product.getProductId());
            txtSize.setText(product.getSize());
            txtPrice.setText(Double.toString(product.getPrice()));
            txtQtyOnHand.setText(Integer.toString(product.getQtyOnHand()));
            comdSupplierName.setValue(product.getSupplierName());

        }
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        ProductService service= SuperFactory.getInstance().getServiceType(ServiceType.PRODUCT);



        Product product=new Product(
                txtProductId.getText(),
                txtProductName.getText(),
                txtSize.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                (String) comdSupplierName.getValue()

        );

        if(service.updatProduct(product)){
            clear();
            loadTable();
        }
    }


}

