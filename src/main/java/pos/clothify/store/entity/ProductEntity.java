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
@Table(name = "Product")

public class ProductEntity {
    @Id
    private Integer idProduct;
    private String ProductName;
    private String Size;
    private Double Price;
    private String QtyOnHand;
    private Integer Status;

    @OneToMany(mappedBy = "productEntity")
    private Set<ProductHasOrderEntity>productHasOrderEntity;

    @OneToMany(mappedBy = "product")
    private Set<SupplierProductEntity>supplierProductEntity;

}
