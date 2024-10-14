package pos.clothify.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="JobRole")
public class JobRoleEntity {
    @Id

    private Integer idJobrole;
    private String JobRoleName;
    private Integer Status;

    @OneToMany(mappedBy = "JobRole")
    private Set<EmployeeEntity>employeeEntity;

    @OneToMany(mappedBy = "jobRoleEntity")
    private  Set<UserEntity>userEntity;

}
