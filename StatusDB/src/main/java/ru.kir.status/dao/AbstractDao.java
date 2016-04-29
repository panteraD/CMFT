package ru.kir.status.dao;

import org.hibernate.SessionFactory;

/**
 * Created by Kirill Zhitelev on 07.03.2016.
 */
public class AbstractDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //dependency injection
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
