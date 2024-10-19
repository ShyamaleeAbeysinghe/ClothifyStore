package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pos.clothify.store.model.Employee;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.EmployeeSrevice;
import pos.clothify.store.util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmplyeeName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

        loadTable();

        EmployeeSrevice service = SuperFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        Integer maxId = service.findByEmployeeMaxId();
        if(maxId==null){
            maxId=0;
        }
        maxId++;

        txtEmployeeId.setText(Integer.toString(maxId));

    }

    private void loadTable() {
        EmployeeSrevice service = SuperFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        tblEmployee.setItems(service.getAllEmployee());
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        EmployeeSrevice service = SuperFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

        Employee employee=new Employee(
                    txtEmployeeId.getText(),
                    txtEmplyeeName.getText(),
                    txtEmail.getText(),
                    txtCompany.getText()

            );

        if(service.addEmployee(employee)){
            clear();
            loadTable();
        }




    }

    private void clear() {
        txtEmployeeId.setText("");
        txtEmplyeeName.setText("");
        txtEmail.setText("");
        txtCompany.setText("");
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        EmployeeSrevice service = SuperFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

        if(service.deleteEmployee(txtEmail.getText())){
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
        EmployeeSrevice service = SuperFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        Employee employee = service.findByEmail(txtEmail.getText());

        if(employee!=null){
            txtEmployeeId.setText(employee.getEmployeeId());
            txtEmplyeeName.setText(employee.getEmployeeName());
            txtCompany.setText(employee.getCompany());
        }

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        EmployeeSrevice service = SuperFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

        Employee employee=new Employee(
                txtEmployeeId.getText(),
                txtEmplyeeName.getText(),
                txtEmail.getText(),
                txtCompany.getText()

        );

        if(service.updateEmployee(employee)){
            clear();
            loadTable();
        }

    }

}

