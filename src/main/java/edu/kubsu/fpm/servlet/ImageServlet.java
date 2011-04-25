package edu.kubsu.fpm.servlet;

import edu.kubsu.fpm.ejb.SessionMapRemote;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: acer
 * Date: 24.04.11
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "imageServlet", urlPatterns = {"/loadimage"})
public class ImageServlet extends HttpServlet {
    @EJB(lookup = "edu.kubsu.fpm.ejb.SessionMapRemote")
    private SessionMapRemote sessionMapBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Context context = new InitialContext();
            SessionMapRemote sessionMap = (SessionMapRemote) context.lookup(SessionMapRemote.class.getName());
            sessionMap.getName();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        String name = sessionMapBean.getName();
        System.out.println(name);
        String test = "test";
        System.out.print(test);
    }
}
