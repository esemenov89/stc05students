package main.controllers.old.servlets;

import main.services.StudentService;
import main.services.StudentServiceImpl;
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
public class ListServlet extends HttpServlet {

    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("delete");

        if (( id != null) && (!id.isEmpty())) {
            deleteStudent(req.getParameter("delete"));
            resp.sendRedirect(req.getContextPath() + "/listStudents");
        }else {
            req.setAttribute("list", studentService.getAllStudents());
            getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
        }
    }

    private void deleteStudent(String id){
        if (id.matches("\\d+")) {
            studentService.deleteStudent(Integer.parseInt(id));
        }
    }
}
