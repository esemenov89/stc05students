package main.model.impl;

import main.model.dao.UserDao;
import main.model.entity.UsersEntity;
import main.services.HibernateSessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import org.hibernate.Query;
import java.util.List;

/**
 *
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Override
    public UsersEntity findLoginAndPassword(String login, String password) {
        UsersEntity user = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from UsersEntity where lower(login) = :paramName and lower(password) = :paramPass");
        query.setParameter("paramName", login.toLowerCase());
        query.setParameter("paramPass", password.toLowerCase());
        List list = query.list();

        if(list.size()>0)
            user = (UsersEntity)list.get(0);

        session.close();
        return user;
}

    private UsersEntity findByPk(int id) {
        UsersEntity user = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from UsersEntity where id = :id");
        query.setParameter("id", id);
        List list = query.list();

        if(list.size()>0)
            user = (UsersEntity)list.get(0);

        session.close();
        return user;
    }
}
