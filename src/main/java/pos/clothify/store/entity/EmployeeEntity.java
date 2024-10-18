package pos.clothify.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Employee")
public class EmployeeEntity {
    @Id
    private Integer idEmployee;
    private String EmployeeName;
    private String Company;
    private String Email;
    private Integer Status;


}
