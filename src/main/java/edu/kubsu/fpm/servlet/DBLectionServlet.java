package edu.kubsu.fpm.servlet;

import edu.kubsu.fpm.ejb.DBImageLocal;
import edu.kubsu.fpm.ejb.DBLectionLocal;
import edu.kubsu.fpm.managed.teacher_ps.classes.CourseLection;
import edu.kubsu.fpm.managed.teacher_ps.classes.PersonalPhoto;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 27.04.12
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "DBLectionServlet", urlPatterns = "/DBLectionServlet")
public class DBLectionServlet extends HttpServlet {
    @EJB
    private DBLectionLocal DBLection;
    private byte[] lectionContent;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lectionId = request.getParameter("lectionId");
        if(lectionId!=null){
            for (CourseLection courseLection:DBLection.getLections()){
                if(courseLection.getId()==Integer.parseInt(lectionId)){
                    lectionContent = courseLection.getContent();
                    break;
                }
            }
        }

        ServletOutputStream stream = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setContentLength(lectionContent.length);
        response.setHeader("Content-Disposition","file");
        stream.write(lectionContent);
        stream.flush();
        stream.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
