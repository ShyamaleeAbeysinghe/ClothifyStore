package pos.clothify.store.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pos.clothify.store.model.AddOrder;
import pos.clothify.store.model.SaveOrder;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.AddOrderServise;
import pos.clothify.store.util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddOrderFormController implements Initializable {


    private ObservableList<AddOrder> orderList;
    private AddOrderServise service;
    private String email;

    public AddOrderFormController(String email){
        this.email=email;
    }
    @FXML
    public TableColumn colPrice;

    @FXML
    private JFXButton btnBack;

    @FXML
    public JFXTextField txtOrderId;

    @FXML
    public ComboBox combProductName;

    @FXML
    public JFXTextField txtCash;

    @FXML
    public JFXTextField txtBalance;

    @FXML
    public JFXTextField txtCustomerName;

    @FXML
    public JFXTextField txtTotal;

    @FXML
    private TableColumn colProductName;

    @FXML
    private TableColumn colQTY;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableView<AddOrder> tblAddOrder;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private JFXTextField txtSize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         orderList = FXCollections.observableArrayList();

        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

         service = SuperFactory.getInstance().getServiceType(ServiceType.ADDORDER);
        combProductName.setItems(service.findAllProduvtName());

        Integer maxId = service.findByMaxId();

        if(maxId==null){
            maxId=0;
        }
        maxId++;
        txtOrderId.setText(Integer.toString(maxId));

        txtTotal.setText("0.0");

    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {

        if(combProductName.getValue().toString().isEmpty() || txtQTY.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else{
            Double price = service.getPrice((String) combProductName.getValue());
            AddOrder addOrder=new AddOrder(
                    (String) combProductName.getValue(),
                    Integer.parseInt(txtQTY.getText()),
                    price

            );
            orderList.add(addOrder);
            tblAddOrder.setItems(orderList);
            double total = Double.parseDouble(txtTotal.getText());
            total+=price*Integer.parseInt(txtQTY.getText());
            txtTotal.setText(Double.toString(total));

            combProductName.setValue(null);
            txtQTY.setText("");
        }



    }

    @FXML
    void btnOnActionCheckOut(ActionEvent event) {
        double total = Double.parseDouble(txtTotal.getText());
        if(total==0){
            new Alert(Alert.AlertType.ERROR,"No items to CheckOut").show();
        }else {
            SaveOrder saveOrder=new SaveOrder(
                    orderList,
                    Integer.parseInt(txtOrderId.getText()),
                    total,
                    txtCustomerName.getText()


            );
            if(service.saveOrder(saveOrder)){
                clear();
                new Alert(Alert.AlertType.INFORMATION,"Checkout Success").show();
            }
        }

    }

    private void clear() {
        Integer maxId = service.findByMaxId();

        if(maxId==null){
            maxId=0;
        }
        maxId++;
        txtOrderId.setText(Integer.toString(maxId));

        txtTotal.setText("0.0");
        txtBalance.setText("");
        txtCash.setText("");
        txtCustomerName.setText("");
        combProductName.setValue(null);
        txtQTY.setText("");
        orderList=FXCollections.observableArrayList();
        tblAddOrder.setItems(orderList);

    }

    @FXML
    public void txtOnReleaseGenarate(KeyEvent keyEvent) {
        double total = Double.parseDouble(txtTotal.getText());
        double cash = Double.parseDouble(txtCash.getText());
        double balance = cash - total;
        txtBalance.setText(Double.toString(balance));
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

