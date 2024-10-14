package pos.clothify.store.reporsitory;

import pos.clothify.store.reporsitory.custom.impl.*;
import pos.clothify.store.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory():instance;
    }

    public static <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case ORDER: return (T)new OrderDaoImpl();
            case SUPPLIER: return (T)new SupplierDaoImpl();
            case EMPLOYEE: return (T)new EmployeeDaoImpl();
            case PRODUCT: return (T)new ProductDaoImpl();
            case USER: return (T)new UserDaoImpl();
            case RETURN:return (T)new ReturnDaoImpl();
            case JOBROLE:return (T)new JobRoleDaoImpl();
            case PRODUCTHASORDER: return (T)new ProductHasOrderDaoImpl();
            case SUPPLIERPRODUCT:return (T)new SupplierProductDaoImpl();

        }
        return null;
    }
}
