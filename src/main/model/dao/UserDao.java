package main.model.dao;

import main.model.entity.UsersEntity;

/**
 * Created by Aleksei Lysov on 20.04.2017.
 */
public interface UserDao {
    UsersEntity findLoginAndPassword(String login, String password);
}
