package edu.kubsu.fpm.servlet;

import edu.kubsu.fpm.ejb.DBVideoLocal;
import edu.kubsu.fpm.managed.classes.media_classes.Video;

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
 * Date: 30.05.12
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "DBVideoServlet", urlPatterns = "/DBVideoServlet")
public class DBVideoServlet extends HttpServlet {
    @EJB
    private DBVideoLocal videoLocal;
    private Video video;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String videoId = request.getParameter("videoId");
        if(videoId!=null){
            video = extractVideoFromLst(videoId);
        }
        response.setContentType("video/mp4");
        OutputStream os = response.getOutputStream();
        os.write(video.getContent());
        os.flush();
    }

    private Video extractVideoFromLst(String videoId) {
        for(Video a: videoLocal.getVideoList()){
            if(a.getId()==Integer.parseInt(videoId)){
                return a;
            }
        }
        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
