package pos.clothify.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Supplie_product")
public class SupplierProductEntity {
    @ManyToOne
    @JoinColumn(name = "Supplier_id")
    private SupplierEntity supplier;

    @ManyToOne
    @JoinColumn(name = "Product_id")
    private ProductEntity product;

}
