package ru.kir.register.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.kir.register.domain.Param;
import ru.kir.register.domain.User;
import ru.kir.register.util.HibernateUtil;

/**
 * Created by Kirill Zhitelev on 16.02.2016.
 */
public class UserDaoImpl implements UserDao {
    private Session currentSession;
    private Transaction currentTransaction;

    public Session openTransactionalCurrentSession() {
        currentSession = HibernateUtil.getInstance().openSession();
        currentTransaction = currentSession.beginTransaction();

        return currentSession;
    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getInstance().openSession();

        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeTransactionalCurrentSession() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public void addUser(User user) {
        currentSession.save(user);
    }

    @Override
    public Param getParam(String paramValue) {
        return (Param) currentSession.createCriteria(Param.class).add(Restrictions.eq("value", paramValue)).uniqueResult();
    }
}
