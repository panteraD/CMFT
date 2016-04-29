package ru.kir.status.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.kir.status.domain.User;

import java.util.List;

/**
 * Created by Kirill Zhitelev on 07.03.2016.
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public void addUser(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public User getUser(String byWhat, String value) {
        Session session = getSessionFactory().openSession();
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq(byWhat, value)).uniqueResult();
        session.close();

        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        return session.createCriteria(User.class).list();
    }
}
