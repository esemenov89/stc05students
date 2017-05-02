package main.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import main.model.entity.User;
import main.services.UserService;

@SuppressWarnings("deprecation")
public class UserController extends SimpleFormController {

    private UserService userService;

    public UserController()	{
        setCommandClass(User.class);
        setCommandName("user");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        User user = (User) command;
        //userService.add(user);
        return new ModelAndView("userSuccess","user",user);
    }

}