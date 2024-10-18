package pos.clothify.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
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
@Table(name = "Supplier")

public class SupplierEntity {
    @Id
    private Integer idSupplier;
    private String SupplierName;
    private String Company;
    private String Email;
    private Integer Status;

@OneToMany(mappedBy = "supplier")
private Set<SupplierProductEntity>supplierProductEntity;

}
