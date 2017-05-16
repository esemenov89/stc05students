package main.services;

import main.model.dao.UserDao;
import main.model.entity.UsersEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by Aleksei Lysov on 20.04.2017.
 */
@Component
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    @Override
    public UsersEntity auth(String login, String password) {
        UsersEntity user = userDao.findLoginAndPassword(login, password);
        LOGGER.debug(user);

        if ((user != null) && (!user.getBlock()))
                return user;
        return null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
