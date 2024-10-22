package pos.clothify.store.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ProductHasOrderEntity;
import pos.clothify.store.entity.ReturnEntity;
import pos.clothify.store.model.ProductHasOrder;
import pos.clothify.store.model.Return;
import pos.clothify.store.model.ViewOrder;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.OrderDao;
import pos.clothify.store.reporsitory.custom.ProductHasOrderDao;
import pos.clothify.store.reporsitory.custom.ReturnDao;
import pos.clothify.store.service.custom.ReturnItemService;
import pos.clothify.store.util.DaoType;

import java.util.List;

public class returnItemServiceImpl implements ReturnItemService {
    @Override
    public Return findByOrderId(Integer orderId) {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        OrderEntity orderEntity = orderDao.findByOrderId(orderId,0);
        if(orderEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found Order").show();
            return null;
        }
        ReturnDao returnDao = DaoFactory.getInstance().getDao(DaoType.RETURN);
        ReturnEntity returnEntity =returnDao.findByOrderId(orderEntity);

        Return returnDto = new ModelMapper().map(orderEntity, Return.class);
        returnDto.setDate(returnEntity.getReturnDate());
        return returnDto;
    }

    @Override
    public ObservableList<ProductHasOrder> orderList(Integer orderId) {
        ObservableList<ProductHasOrder>orders= FXCollections.observableArrayList();

        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        OrderEntity orderEntity = orderDao.findByOrderId(orderId,0);

        ProductHasOrderDao productHasOrderDao = DaoFactory.getInstance().getDao(DaoType.PRODUCTHASORDER);
        List<ProductHasOrderEntity> byOrderId = productHasOrderDao.findByOrderId(orderEntity);

        byOrderId.forEach(productHasOrderEntity -> {
            ProductHasOrder productHasOrder = new ModelMapper().map(productHasOrderEntity, ProductHasOrder.class);
            productHasOrder.setPrice(productHasOrderEntity.getProductEntity().getPrice());
            orders.add(productHasOrder);

        });
        return orders;
    }
}
