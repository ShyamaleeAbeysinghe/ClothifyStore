package pos.clothify.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ViewOrder {
    private String OrderId;
    private String customerName;
    private double total;
}
