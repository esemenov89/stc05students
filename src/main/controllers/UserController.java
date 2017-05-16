package main.controllers;

import main.model.entity.UsersEntity;
import org.springframework.web.servlet.ModelAndView;

import main.services.UserService;

@SuppressWarnings("deprecation")
public class UserController {

    private UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected ModelAndView onSubmit(Object command) throws Exception {
        UsersEntity user = (UsersEntity) command;
        //userService.add(user);
        return new ModelAndView("userSuccess","user",user);
    }

}
