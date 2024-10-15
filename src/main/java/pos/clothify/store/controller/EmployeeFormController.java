package pos.clothify.store.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pos.clothify.store.model.Employee;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmplyeeName;

    @FXML
    void btnOnActionAdd(ActionEvent event) {

        if(txtEmployeeId==null || txtEmplyeeName==null || txtEmail==null || txtCompany==null){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
        }else{
            Employee employee=new Employee(
                    txtEmployeeId.getText(),
                    txtEmplyeeName.getText(),
                    txtEmail.getText(),
                    txtCompany.getText()

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

