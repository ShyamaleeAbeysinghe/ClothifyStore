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
@Table(name = "User")
public class UserEntity {
    @Id
    private Integer idUser;
    private String FirstName;
    private String LastName;
    private String Address;
    private String NIC;
    private String Contact;
    private String UserEmail;
    private String Password;
    private Integer Status;

    @ManyToOne
    @JoinColumn(name = "JobRole_id")
    private JobRoleEntity jobRoleEntity;
}
