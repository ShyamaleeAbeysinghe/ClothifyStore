package pos.clothify.store.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ReturnEntity;
import pos.clothify.store.model.ViewOrder;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.OrderDao;
import pos.clothify.store.reporsitory.custom.ReturnDao;
import pos.clothify.store.service.custom.ViewOrderService;
import pos.clothify.store.util.DaoType;

import java.util.Date;
import java.util.List;

public class ViewOrderServiceImpl implements ViewOrderService {
    @Override
    public ObservableList<ViewOrder> findAllOrders() {
        ObservableList<ViewOrder> orderList= FXCollections.observableArrayList();
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        List<OrderEntity> all = orderDao.findAll();

        all.forEach(orderEntity -> {
            ViewOrder viewOrder = new ModelMapper().map(orderEntity, ViewOrder.class);
            orderList.add(viewOrder);
        });
        return orderList;

    }

    @Override
    public ViewOrder findByOrderId(Integer orderId) {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        OrderEntity orderEntity = orderDao.findByOrderId(orderId);
        if(orderEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found Order").show();
            return null;
        }

        ViewOrder viewOrder = new ModelMapper().map(orderEntity, ViewOrder.class);

        return viewOrder;
    }

    @Override
    public Boolean returnOrder(Integer orderId) {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        OrderEntity orderEntity = orderDao.findByOrderId(orderId);
        orderEntity.setStatus(0);
        orderDao.update(orderEntity);
        ReturnDao returnDao = DaoFactory.getInstance().getDao(DaoType.RETURN);
        ReturnEntity returnEntity=new ReturnEntity();
        returnEntity.setOrder(orderEntity);
        returnEntity.setReturnDate(new Date().toString());
        returnEntity.setStatus(1);

        returnDao.save(returnEntity);
        return true;
    }
}
