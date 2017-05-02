package main.controllers;

import jdk.nashorn.internal.ir.IdentNode;
import main.model.dao.StudentDao;
import main.model.entity.Student;
import main.model.entity.User;
import main.services.StudentService;
import main.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Olesya on 27.04.2017.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
    private static final Logger LOGGER = Logger.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public void sayHello() {
        LOGGER.debug("STUDEEEEEEEEEENT");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:student");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "age", required = true) String age,
                              @RequestParam(value = "group_id", required = true) String group_id) {
        ModelAndView mav = new ModelAndView();
        LOGGER.debug("STUDEEEEEEEEEEEEEEEEEEEEEEEEEEENT POOOOOOOOOOOOOOOOST");
        Student student = new Student(name, Integer.parseInt(age),Long.parseLong(group_id));
        studentService.insert(student);
        mav.setViewName("redirect:list");
        return mav;
    }

}
