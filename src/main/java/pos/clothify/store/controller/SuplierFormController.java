package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import pos.clothify.store.model.Supplier;
import pos.clothify.store.reporsitory.custom.SupplierDao;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.SupplierService;
import pos.clothify.store.util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class SuplierFormController implements Initializable {

    private String role;
    private String email;

    public SuplierFormController(String role,String email){
        this.role=role;
        this.email=email;
    }
    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnBAck;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colSuplierName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSuplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

        loadTable();

        SupplierService service = SuperFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

        Integer maxId = service.findByMaxId();
        if(maxId==null){
            maxId=0;
        }
        maxId++;
        txtSupplierId.setText(Integer.toString(maxId));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal!=null){
                addValueToText(newVal);
            }
        });

    }

    private void addValueToText(Supplier newVal) {
        txtSupplierId.setText(newVal.getSupplierId());
        txtSupplierName.setText(newVal.getSupplierName());
        txtEmail.setText(newVal.getEmail());
        txtCompany.setText(newVal.getCompany());
    }

    private void loadTable() {
        SupplierService service = SuperFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        tblSupplier.setItems(service.findAllSupplier());
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        SupplierService service = SuperFactory.getInstance().getServiceType(ServiceType.SUPPLIER);


        Supplier supplier=new Supplier(
                    txtSupplierId.getText(),
                    txtSupplierName.getText(),
                    txtCompany.getText(),
                    txtEmail.getText()
            );

        if(service.addSupplier(supplier)){
            clear();
            loadTable();
            Integer maxId = service.findByMaxId();
            if(maxId==null){
                maxId=0;
            }
            maxId++;
            txtSupplierId.setText(Integer.toString(maxId));
        }




    }

    private void clear() {
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtEmail.setText("");
        txtCompany.setText("");
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        SupplierService service = SuperFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        if(service.deleteSupplier(txtEmail.getText())){
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
        SupplierService service = SuperFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        Supplier supplier = service.findBySupplierEmail(txtEmail.getText());

        if(supplier!=null){
            txtSupplierId.setText(supplier.getSupplierId());
            txtSupplierName.setText(supplier.getSupplierName());
            txtCompany.setText(supplier.getCompany()

            );
        }

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {

        SupplierService service = SuperFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

        Supplier supplier=new Supplier(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );

        if(service.updateUser(supplier)){
            clear();
            loadTable();

            Integer maxId = service.findByMaxId();
            if(maxId==null){
                maxId=0;
            }
            maxId++;
            txtSupplierId.setText(Integer.toString(maxId));
        }

    }
    @FXML
    void btnOnActionBack(ActionEvent event) {
        Stage curruntStage=(Stage) btnBAck.getScene().getWindow();
        Stage stageNew=new Stage();
        if(role.equals("Admin")){
            try {
                FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/AdminDashboardFormController.fxml"));
                loads.setController(new AdminDashboardController());
                Parent load = loads.load();
                stageNew.setScene(new Scene(load));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            try {
                FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/UserDashboardFormController.fxml"));
                loads.setController(new UserDashboardController(email));
                Parent load = loads.load();
                stageNew.setScene(new Scene(load));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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


}

