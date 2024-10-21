package pos.clothify.store.model;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveOrder {
    private ObservableList<AddOrder> orderItemsList;
    private Integer orderId;
    private Double total;
    private String customerName;
}
