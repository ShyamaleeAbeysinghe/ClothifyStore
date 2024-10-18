package pos.clothify.store.service.custom;

import pos.clothify.store.model.Employee;
import pos.clothify.store.service.SuperService;

public interface EmployeeSrevice extends SuperService {
    Integer findByEmployeeMaxId();

    boolean addEmployee(Employee employee);

    Employee findByEmail(String email);
}
