package pos.clothify.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductHasOrder {
    private String orderId;
    private String productName;
    private Integer qty;
    private Double price;
}
