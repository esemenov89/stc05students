package main.controllers.listeners;

import main.controllers.old.servlets.LoginServlet;
import main.util.MailUtil;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Aleksei Lysov on 20.04.2017.
 */
public class AppStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader().getResource("log4j.properties"));
        String email = servletContextEvent.getServletContext().getInitParameter("ADMIN_EMAIL");
        System.out.println(email);
        MailUtil.send(email, "Start tomcat server");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
