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
import pos.clothify.store.entity.ReturnEntity;
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
    private final String email;

    public AddOrderFormController(String email) {
        this.email = email;
    }
    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

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

        if (maxId == null) {
            maxId = 0;
        }
        maxId++;
        txtOrderId.setText(Integer.toString(maxId));

        txtTotal.setText("0.0");
        tblAddOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                addValueToText(newVal);

            }
        });

    }

    private void addValueToText(AddOrder newVal) {
        combProductName.setValue(newVal.getProductName());
        txtQTY.setText(Integer.toString(newVal.getQty()));
        btnAdd.setDisable(true);
        btnUpdate.setDisable(false);
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {

        if (combProductName.getValue().toString().isEmpty() || txtQTY.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Some Fields are empty").show();
        } else {
            String productName = (String) combProductName.getValue();
            Double price = service.getPrice(productName);
            int qty = Integer.parseInt(txtQTY.getText());
            boolean itemFound=false;

            for (int i = 0; i < orderList.size(); i++) {
                AddOrder addOrder = orderList.get(i);
                if (productName.equals(addOrder.getProductName())) {
                    itemFound=true;
                    int typedQty = Integer.parseInt(txtQTY.getText());
                    int oldQty = addOrder.getQty();
                    double priceOne = addOrder.getPrice() / oldQty;
                    int newQty = typedQty+oldQty;
                    double newPrice = newQty * priceOne;

                    addOrder.setQty(newQty);
                    addOrder.setPrice(newPrice);
                    orderList.set(i,addOrder);
                    double total = Double.parseDouble(txtTotal.getText());
                    total += (priceOne * typedQty);
                    txtTotal.setText(Double.toString(total));
                }
            }

            if (!itemFound){
                AddOrder addOrder = new AddOrder(
                        productName,
                        qty,
                        price*qty

                );
                orderList.add(addOrder);
                double total = Double.parseDouble(txtTotal.getText());
                total += price * Integer.parseInt(txtQTY.getText());
                txtTotal.setText(Double.toString(total));
            }
            tblAddOrder.setItems(orderList);

            combProductName.setValue(null);
            txtQTY.setText("");
        }


    }

    @FXML
    void btnOnActionCheckOut(ActionEvent event) {
        double total = Double.parseDouble(txtTotal.getText());
        if (total == 0) {
            new Alert(Alert.AlertType.ERROR, "No items to CheckOut").show();
        } else {
            SaveOrder saveOrder = new SaveOrder(
                    orderList,
                    Integer.parseInt(txtOrderId.getText()),
                    total,
                    txtCustomerName.getText()


            );
            if (service.saveOrder(saveOrder)) {
                clear();
                new Alert(Alert.AlertType.INFORMATION, "Checkout Success").show();
            }
        }

    }

    private void clear() {
        Integer maxId = service.findByMaxId();

        if (maxId == null) {
            maxId = 0;
        }
        maxId++;
        txtOrderId.setText(Integer.toString(maxId));

        txtTotal.setText("0.0");
        txtBalance.setText("");
        txtCash.setText("");
        txtCustomerName.setText("");
        combProductName.setValue(null);
        txtQTY.setText("");
        orderList = FXCollections.observableArrayList();
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
        Stage curruntStage = (Stage) btnBack.getScene().getWindow();
        Stage stageNew = new Stage();

        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/view/UserDashboardFormController.fxml"));
            load.setController(new UserDashboardController(email));
            Parent parent = load.load();
            stageNew.setScene(new Scene(parent));
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
    void btnOnActionUpdate(ActionEvent event) {
        String productName = (String) combProductName.getValue();
        for (int i = 0; i < orderList.size(); i++) {
            AddOrder addOrder = orderList.get(i);
            if (productName.equals(addOrder.getProductName())) {
                int typedQty = Integer.parseInt(txtQTY.getText());
                int oldQty = addOrder.getQty();
                double priceOne = addOrder.getPrice() / oldQty;
                int newQty = typedQty+oldQty;
                double newPrice = newQty * priceOne;

                addOrder.setQty(newQty);
                addOrder.setPrice(newPrice);
                orderList.set(i,addOrder);
                double total = Double.parseDouble(txtTotal.getText());
                total += (priceOne * typedQty);
                txtTotal.setText(Double.toString(total));
            }
        }
        tblAddOrder.setItems(orderList);

        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);

        combProductName.setValue(null);
        txtQTY.setText("");
    }
}

