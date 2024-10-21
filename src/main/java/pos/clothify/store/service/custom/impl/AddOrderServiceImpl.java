package pos.clothify.store.service.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.modelmapper.ModelMapper;
import pos.clothify.store.entity.OrderEntity;
import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.entity.ProductHasOrderEntity;
import pos.clothify.store.model.AddOrder;
import pos.clothify.store.model.Product;
import pos.clothify.store.model.SaveOrder;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.OrderDao;
import pos.clothify.store.reporsitory.custom.ProductDao;
import pos.clothify.store.reporsitory.custom.ProductHasOrderDao;
import pos.clothify.store.service.SuperFactory;
import pos.clothify.store.service.SuperService;
import pos.clothify.store.service.custom.AddOrderServise;
import pos.clothify.store.service.custom.ProductService;
import pos.clothify.store.util.DaoType;
import pos.clothify.store.util.HibernateUtil;
import pos.clothify.store.util.ServiceType;

import java.util.List;

public class AddOrderServiceImpl implements AddOrderServise {

    @Override
    public Integer findByMaxId() {
        OrderDao dao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        return dao.findByMaxId();
    }

    @Override
    public ObservableList<String> findAllProduvtName() {
        ObservableList<String> productNameList= FXCollections.observableArrayList();
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        List<ProductEntity> all = dao.findAll();

        all.forEach(productEntity -> {
            productNameList.add(productEntity.getProductName());
        });

        return productNameList;
    }

    @Override
    public Double getPrice(String name) {
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        ProductEntity productEntity = dao.findByProductName(name);

        return productEntity.getPrice();
    }

    @Override
    public Boolean saveOrder(SaveOrder saveOrder) {
        OrderDao dao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setIdOrder(saveOrder.getOrderId());
        orderEntity.setCustomerName(saveOrder.getCustomerName());
        orderEntity.setTotal(saveOrder.getTotal());
        orderEntity.setStatus(1);

        dao.save(orderEntity);
        ProductHasOrderDao productHasOrderDao = DaoFactory.getInstance().getDao(DaoType.PRODUCTHASORDER);

        saveOrder.getOrderItemsList().forEach(addOrder -> {
            ProductHasOrderEntity productHasOrderEntity=new ProductHasOrderEntity();
            productHasOrderEntity.setOrder(orderEntity);
            ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
            ProductEntity productEntity = productDao.findByProductName(addOrder.getProductName());

            productHasOrderEntity.setProductEntity(productEntity);
            productHasOrderEntity.setQty(Integer.toString(addOrder.getQty()));

            productHasOrderDao.save(productHasOrderEntity);
        });
        return true;
    }
}
