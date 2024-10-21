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
@Table(name = "Returns")
public class ReturnEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idReturns;
    private String ReturnDate;
    private Integer Status;

    @OneToOne
    @JoinColumn(name = "Order_idOrder")
    private OrderEntity order;


}
