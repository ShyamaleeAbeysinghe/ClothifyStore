package pos.clothify.store.service.custom.impl;

import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import pos.clothify.store.entity.EmployeeEntity;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.model.Employee;
import pos.clothify.store.model.User;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.EmployeeDao;
import pos.clothify.store.reporsitory.custom.UserDao;
import pos.clothify.store.service.custom.EmployeeSrevice;
import pos.clothify.store.util.DaoType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeServiceImpl implements EmployeeSrevice {
    @Override
    public Integer findByEmployeeMaxId() {
        EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        return dao.findByMaxId();

    }

    @Override
    public boolean addEmployee(Employee employee) {
        if(employee.getEmployeeId().isEmpty() || employee.getEmployeeName().isEmpty() || employee.getEmail().isEmpty() || employee.getCompany().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Some Fields are empty").show();
            return false;

        }else if (!validateEmail(employee.getEmail())){
            new Alert(Alert.AlertType.ERROR,"Incorrect Email").show();
            return false;
        }else{
            EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
            EmployeeEntity employeeEntityGet = dao.findBySupplierEmail(employee.getEmail());

            if(employeeEntityGet==null){
                EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);
                employeeEntity.setStatus(1);

                boolean save = dao.save(employeeEntity);
                if(save==true){
                    new Alert(Alert.AlertType.INFORMATION).show();
                    return true;
                }else{
                    new Alert(Alert.AlertType.ERROR).show();
                    return false;
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Email is already exist").show();
                return false;
            }
        }

    }

    @Override
    public Employee findByEmail(String email) {
        EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        EmployeeEntity employeeEntity = dao.findBySupplierEmail(email);
        if(employeeEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found User").show();
            return null;
        }
        return new ModelMapper().map(employeeEntity, Employee.class);
    }

    public boolean validateEmail(String emailStr) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}
