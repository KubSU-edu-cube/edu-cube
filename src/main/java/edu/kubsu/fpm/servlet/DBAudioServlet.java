package edu.kubsu.fpm.servlet;

import edu.kubsu.fpm.ejb.DBAudioLocal;
import edu.kubsu.fpm.managed.teacher_ps.classes.PersonalPhoto;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 17.05.12
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "DBAudioServlet", urlPatterns = "/DBAudioServlet")
public class DBAudioServlet extends HttpServlet {
    @EJB
    private DBAudioLocal audioLocal;
    private byte[] audio;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String audioId = request.getParameter("audioId");
        if(audioId!=null){
            audio = audioLocal.getAudioList().get(Integer.parseInt(audioId));
        }
        response.setContentType("audio/mpeg");
        OutputStream os = response.getOutputStream();
        os.write(audio);
        os.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
