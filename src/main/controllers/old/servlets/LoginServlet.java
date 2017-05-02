package main.controllers.old.servlets;

import main.model.entity.User;
import main.services.UserService;
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
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String passwor = req.getParameter("password");

        User user = null;
        if ((user = userService.auth(login, passwor)) != null){
            req.getSession().setAttribute("login", login);
            LOGGER.debug("login: " + login);
            resp.sendRedirect(req.getContextPath() + "/listStudents");
        }

//        if (("user".equals(login)) && ("password".equals(passwor)))
//        if (("user".equals(login)) && ("password".equals(passwor)))
//            resp.sendRedirect(req.getContextPath() + "/listStudents");
//        else
//            resp.sendRedirect(req.getContextPath() + "/error");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
}
