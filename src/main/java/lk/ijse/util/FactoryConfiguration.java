package lk.ijse.util;

import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;


    private FactoryConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/Hibernate.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Configuration configuration = new Configuration();

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Student.class)
                .addAnnotatedClass(Registration.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(User.class);

        //build session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        if ( factoryConfiguration == null){
            return new FactoryConfiguration();
        }
        return new FactoryConfiguration();
    }


    public FactoryConfiguration(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
