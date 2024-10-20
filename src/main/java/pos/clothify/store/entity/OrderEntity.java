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
@Table(name = "Orders")

public class OrderEntity {
    @Id

    private Integer idOrder;
    private String CustomerName;
    private Double Total;
    private Integer Status;

    @OneToMany(mappedBy = "order")
    private Set<ProductHasOrderEntity>productHasOrderEntity;


}
