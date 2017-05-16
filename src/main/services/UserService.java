package main.services;

import main.model.entity.UsersEntity;

/**
 * Created by Aleksei Lysov on 20.04.2017.
 */
public interface UserService {
    UsersEntity auth(String login, String password);
}
