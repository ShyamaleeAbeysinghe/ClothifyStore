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
@Table(name = "Product_has_order")
public class ProductHasOrderEntity {
    @Id
    private Integer idProductHasOrder;
    private String Qty;

    @ManyToOne
    @JoinColumn(name = "Product_id" )
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "Order_id")
    private OrderEntity order;

}
