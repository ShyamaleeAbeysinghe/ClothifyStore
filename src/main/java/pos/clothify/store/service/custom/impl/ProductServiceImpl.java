package pos.clothify.store.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import pos.clothify.store.entity.ProductEntity;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.entity.SupplierProductEntity;
import pos.clothify.store.entity.UserEntity;
import pos.clothify.store.model.Product;
import pos.clothify.store.model.User;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.*;
import pos.clothify.store.service.custom.ProductService;
import pos.clothify.store.util.DaoType;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public boolean addProduct(Product product) {
        if (product.getProductId().isEmpty() || product.getProductName().isEmpty() || product.getSize().isEmpty() || product.getPrice()==0 || product.getQtyOnHand()==0 || product.getSupplierName().isEmpty() ) {
            new Alert(Alert.AlertType.ERROR, "Some Fields are empty").show();

            return false;

        }else {
            ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
            ProductEntity productEntity = productDao.findByProductName(product.getProductName());

            if(productEntity==null){
                SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
                SupplierEntity supplierEntity = supplierDao.getSupplierByName(product.getSupplierName());

                if(supplierEntity==null){
                    new Alert(Alert.AlertType.ERROR, "can not found supplier").show();

                    return false;
                }

                ProductEntity productEntity1 = new ModelMapper().map(product, ProductEntity.class);
                productEntity1.setStatus(1);







               boolean save = productDao.save(productEntity1);
                System.out.println(productEntity1);

                SupplierProductEntity supplierProductEntity=new SupplierProductEntity();

                supplierProductEntity.setProduct(productEntity1);

                supplierProductEntity.setSupplier(supplierEntity);

                SupplierProductDao supplierProductDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIERPRODUCT);
                supplierProductDao.save(supplierProductEntity);

                return true;
            }else {
                new Alert(Alert.AlertType.ERROR, "this name already exist").show();

                return false;
            }

        }


    }

    @Override
    public Integer findByMaxId() {
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        return dao.findByMaxId();
    }

    @Override
    public ObservableList<String> getSupplierName() {
        ObservableList<String> supplierNameLIst= FXCollections.observableArrayList();
        SupplierDao dao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        List<SupplierEntity> all = dao.findAll();

        all.forEach(supplierEntity -> {
            supplierNameLIst.add(supplierEntity.getSupplierName());
        });

        return supplierNameLIst;

    }

    @Override
    public ObservableList<Product> getAllProducts() {
       ObservableList<Product> productList = FXCollections.observableArrayList();
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        List<ProductEntity> all = dao.findAll();

        all.forEach(productEntity -> {
            Product product = new ModelMapper().map(productEntity, Product.class);
            SupplierProductDao dao1 = DaoFactory.getInstance().getDao(DaoType.SUPPLIERPRODUCT);
            SupplierProductEntity supplierProductEntity = dao1.findByProduct(productEntity);


            product.setSupplierName(supplierProductEntity.getSupplier().getSupplierName());
            productList.add(product);
        });
        return productList;
    }

    @Override
    public Product findProductName(String name) {
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        ProductEntity productEntity = dao.findByProductName(name);
        if(productEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found Product").show();
            return null;
        }
        return new ModelMapper().map(productEntity, Product.class);
    }

    @Override
    public boolean updatProduct(Product product) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        ProductEntity productEntity = dao.findByProductName(product.getProductName());

        if(productEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found Product").show();
            return false;
        }else{
            productEntity.setSize(product.getSize());
            productEntity.setPrice(product.getPrice());
            productEntity.setQtyOnHand(Integer.toString(product.getQtyOnHand()));
            //productEntity.setSupplierProductEntity(supplierDao.getSupplierByName(product.getSupplierName()));


            dao.update(productEntity);
            new Alert(Alert.AlertType.INFORMATION, "Update Succses").show();


            return true;
        }
    }

    @Override
    public boolean deleteProduct(String name) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        ProductEntity productEntity = dao.findByProductName(name);

        if(productEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found Product").show();
            return false;
        }else{
            productEntity.setStatus(0);
            dao.update(productEntity);
            new Alert(Alert.AlertType.INFORMATION, "Delete Succses").show();
            return true;
        }
    }


}
