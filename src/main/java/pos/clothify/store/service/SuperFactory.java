package pos.clothify.store.service;

import pos.clothify.store.service.custom.impl.*;
import pos.clothify.store.util.ServiceType;

public class SuperFactory {

   private static SuperFactory instance;

    private SuperFactory(){}

    public static SuperFactory getInstance(){
        return instance==null?instance=new SuperFactory():instance;
    }
    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case USER:return (T)new UserSeviceImpl();
            case ADDORDER:return (T)new AddOrderServiceImpl();
            case LOGIN:return (T)new LoginServiceImpl();
            case PRODUCT:return (T)new ProductServiceImpl();
            case EMPLOYEE:return (T)new EmployeeServiceImpl();
            case SUPPLIER:return (T)new SupplierServiceImpl();
            case VIEWODER:return (T)new ViewOrderServiceImpl();
            case RETURN:return (T)new returnItemServiceImpl();

        }
        return null;
    }
}
