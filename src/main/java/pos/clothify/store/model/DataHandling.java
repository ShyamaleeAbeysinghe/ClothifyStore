package pos.clothify.store.model;

import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class DataHandling {
    private final SimpleStringProperty email=new SimpleStringProperty();
}
