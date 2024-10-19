package pos.clothify.store.service.custom;

import javafx.collections.ObservableList;
import pos.clothify.store.model.Employee;
import pos.clothify.store.model.Supplier;
import pos.clothify.store.model.User;
import pos.clothify.store.service.SuperService;

public interface EmployeeSrevice extends SuperService {
    Integer findByEmployeeMaxId();

    boolean addEmployee(Employee employee);

    Employee findByEmail(String email);

    ObservableList<Employee> getAllEmployee();

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(String email);
}
