package pos.clothify.store.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import pos.clothify.store.entity.SupplierEntity;
import pos.clothify.store.model.Supplier;
import pos.clothify.store.reporsitory.DaoFactory;
import pos.clothify.store.reporsitory.SuperDao;
import pos.clothify.store.reporsitory.custom.SupplierDao;
import pos.clothify.store.service.custom.SupplierService;
import pos.clothify.store.util.DaoType;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public Integer findByMaxId() {
        SupplierDao dao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return dao.findByMaxId();
    }

    @Override
    public Supplier findBySupplierEmail(String email) {

        SupplierDao dao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = dao.findBySupplierEmail(email);
        if(supplierEntity==null){
            new Alert(Alert.AlertType.ERROR, "Can not found Supplier").show();
            return null;
        }

        return new ModelMapper().map(supplierEntity,Supplier.class);
    }

    @Override
    public boolean addSupplier(Supplier supplier) {

        if(supplier.getSupplierId().isEmpty() || supplier.getSupplierName().isEmpty() ||supplier.getEmail().isEmpty() || supplier.getCompany().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Some Feilds Are Empty").show();
            return false;

        }else if(!validateEmail(supplier.getEmail())){
            new Alert(Alert.AlertType.ERROR, "Incorrect Email").show();
            return false;
        }else{
            SupplierDao dao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
            SupplierEntity supplierEntityGet = dao.findBySupplierEmail(supplier.getEmail());

            if(supplierEntityGet==null){
                SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
                supplierEntity.setStatus(1);

                boolean save = dao.save(supplierEntity);
                if (save == true) {
                    new Alert(Alert.AlertType.INFORMATION).show();
                    return true;

                } else {
                    new Alert(Alert.AlertType.ERROR).show();
                    return false;

                }

            }else {
                new Alert(Alert.AlertType.ERROR, "Email is already exist").show();
                return false;
            }
        }

    }

    @Override
    public ObservableList<Supplier> findAllSupplier() {
    ObservableList<Supplier> supplierList= FXCollections.observableArrayList();
        SupplierDao dao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        List<SupplierEntity> all = dao.findAll();

        all.forEach(supplierEntity -> {
            Supplier supplier = new ModelMapper().map(supplierEntity, Supplier.class);
            supplierList.add(supplier);
        });
        return supplierList;
    }

    @Override
    public boolean updateUser(Supplier supplier) {
        SupplierDao dao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = dao.findBySupplierEmail(supplier.getEmail());

        if(supplierEntity==null){
            new Alert(Alert.AlertType.ERROR, "Supplier is not found").show();
            return false;
        }else {
            supplierEntity.setSupplierName(supplier.getSupplierName());
            supplierEntity.setCompany(supplier.getCompany());
            supplierEntity.setSupplierProductEntity(null);


            dao.update(supplierEntity);
            new Alert(Alert.AlertType.INFORMATION, "update succses").show();
            return true;
        }

    }

    @Override
    public boolean deleteSupplier(String email) {
        SupplierDao dao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = dao.findBySupplierEmail(email);
        if (supplierEntity==null){
            new Alert(Alert.AlertType.ERROR, "Not found Suplier").show();
            return false;
        }else {
            supplierEntity.setSupplierProductEntity(null);
            supplierEntity.setStatus(0);
            dao.update(supplierEntity);
            return true;
        }


    }

    public boolean validateEmail(String emailStr) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}
