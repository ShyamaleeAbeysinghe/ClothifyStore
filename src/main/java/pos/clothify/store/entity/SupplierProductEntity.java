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
@Table(name = "Supplie_product")
public class SupplierProductEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSupplieProduct;
    @ManyToOne
    @JoinColumn(name = "Supplier_id")
    private SupplierEntity supplier;

    @ManyToOne
    @JoinColumn(name = "Product_id")
    private ProductEntity product;

}
