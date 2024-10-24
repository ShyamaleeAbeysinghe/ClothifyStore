package pos.clothify.store.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pos.clothify.store.entity.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(JobRoleEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(ProductHasOrderEntity.class)
                .addAnnotatedClass(ReturnEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(SupplierProductEntity.class)

                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();

    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
