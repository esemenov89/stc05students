package main.controllers.old.servlets;

import main.model.entity.Student;
import main.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aleksei Lysov on 19.04.2017.
 */
public class StudentServlet extends HttpServlet {

    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    private static final Logger LOGGER = Logger.getLogger(StudentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("id");
        String name = "", age = "", group_id = "";

        if ((studentId != null) && (studentId.matches("\\d+"))) {
            req.setAttribute("id", studentId);
            Student student = studentService.findById(Integer.parseInt(studentId));
            if (student != null){
                name = student.getName();
                age = String.valueOf(student.getAge());
                group_id = String.valueOf(student.getGroupId());
            }
        }

        req.setAttribute("name", name);
        req.setAttribute("age", age);
        req.setAttribute("group_id", group_id);

        req.getRequestDispatcher("/student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            long group_id = Long.parseLong(req.getParameter("group_id"));
            String id = req.getParameter("student_id");

            if ((id == null) || ("null".equals(id))) {
                Student student = new Student(name, age, group_id);
                studentService.insert(student);
            }else{
                Student student = studentService.findById(Integer.parseInt(id));
                student.setName(name);
                student.setAge(age);
                student.setGroupId(group_id);
                studentService.update(student);
            }
        }catch (Exception e){
            LOGGER.error(e);
        }
        resp.sendRedirect(req.getContextPath() + "/listStudents");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

}
