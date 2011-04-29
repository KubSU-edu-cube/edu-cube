package edu.kubsu.fpm.TestingModule;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Марина
 * Date: 29.04.11
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */

@WebServlet(name = "TestServlet")
public class TestServlet extends HttpServlet{
                                                    // Все-таки это лучше сделать через JSP
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
          try{
              out.println("ui:composition template=\"templates/stud_template.xhtml\"\n" +
                      "                xmlns:ui=\"http://java.sun.com/jsf/facelets\"\n" +
                      "                xmlns:h=\"http://java.sun.com/jsf/html\"\n" +
                      "                xmlns:a4j=\"http://richfaces.org/a4j\"\n" +
                      "                xmlns=\"http://www.w3.org/1999/xhtml\">");
              out.println("<ui:define name=\"page_content\">");
              out.println("Здесь будет формироваться тестовый вопрос");
              out.println("</ui:define>");
              out.println("</ui:composition>");
          }
          finally {
              out.close();
          }
      }

      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
      }

      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
      }
}
