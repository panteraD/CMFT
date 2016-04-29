package ru.kir.register.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.kir.register.domain.User;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Kirill Zhitelev on 16.02.2016.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        if(sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private synchronized static void createSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addProperties(getProperties());
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        sessionFactory = configuration.addAnnotatedClass(User.class)
                .buildSessionFactory(builder.build());
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try {
            //create in resources folder this file and set properties
            properties.load(HibernateUtil.class.getResourceAsStream("/hibernate/hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
