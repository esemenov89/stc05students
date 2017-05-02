package main.controllers.listeners;

import main.util.MailUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aleksei Lysov on 20.04.2017.
 */
public class MyRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        if (("/student".equals(request.getServletPath()))
                && ("POST".equals(request.getMethod()))
            && ((request.getParameter("id") == null)||("null".equals(request.getParameter("id"))))){//&& ()
            String email = servletRequestEvent.getServletContext().getInitParameter("ADMIN_EMAIL");
            System.out.println(email);
            MailUtil.send(email, "Add new user");
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
