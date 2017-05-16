package main.controllers;

import main.model.entity.StudentEntity;
import main.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/list/student")
public class StudentController {
    private static final Logger LOGGER = Logger.getLogger(StudentController.class);

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.GET)
    public ModelAndView sayHello() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/list/student");
        return mav;
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    public ModelAndView addStudent(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "age", required = true) String age,
                              @RequestParam(value = "group_id", required = true) String group_id) {
        ModelAndView mav = new ModelAndView();
        StudentEntity student = new StudentEntity(name, Integer.parseInt(group_id),Integer.parseInt(age));
        studentService.insert(student);
        mav.setViewName("redirect:/list");
        return mav;
    }

    @RequestMapping(value = "/changeStudent",method = RequestMethod.GET)
    public ModelAndView changeStudentGet(@RequestParam(value = "id", required = true) String id) {
        ModelAndView mav = new ModelAndView();
        StudentEntity student = studentService.findById(Integer.parseInt(id));
        mav.addObject("name", student.getName());
        mav.addObject("age", student.getAge());
        mav.addObject("group_id", student.getGroupId());

        //studentService.deleteStudent(Integer.parseInt(id));
        mav.setViewName("/list/student");
        return mav;
    }

    @RequestMapping(value = "/changeStudent",method = RequestMethod.POST)
    public ModelAndView changeStudentPost(@RequestParam(value = "id", required = true) String id,
                                          @RequestParam(value = "name", required = true) String name,
                                          @RequestParam(value = "age", required = true) String age,
                                          @RequestParam(value = "group_id", required = true) String group_id) {
        ModelAndView mav = new ModelAndView();
        studentService.deleteStudent(Integer.parseInt(id));
        StudentEntity student = new StudentEntity(name, Integer.parseInt(group_id),Integer.parseInt(age));
        studentService.insert(student);
        mav.setViewName("redirect:/list");
        return mav;
    }

    @RequestMapping(value = "/deleteStudent",method = RequestMethod.GET)
    public ModelAndView deleteStudent(@RequestParam(value = "id", required = true) String id) {
        ModelAndView mav = new ModelAndView();
        studentService.deleteStudent(Integer.parseInt(id));
        mav.setViewName("redirect:/list");
        return mav;
    }

}
