package edu.kubsu.fpm.servlet;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import edu.kubsu.fpm.ejb.DBAudioLocal;
import edu.kubsu.fpm.managed.teacher_ps.classes.PersonalPhoto;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 17.05.12
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "DBAudioServlet", urlPatterns = "/DBAudioServlet")
public class DBAudioServlet extends HttpServlet {
    private byte[] audio;
    List<byte[]> list;

    public DBAudioServlet() {
        File file = new File("C:\\Users\\Andrey\\Downloads\\Lalo Project & Aelyn - Listen to me, looking at me.mp3");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            byte[] buffer = new byte[10485760];
            fis.read(buffer);
            String str = Base64.encode(buffer);
            byte[] result = Base64.decode(str);
            List<byte[]> list = new ArrayList<byte[]>();
            list.add(result);
            this.list = list;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String audioId = request.getParameter("audioId");
        if(audioId!=null){
            audio = list.get(Integer.parseInt(audioId));
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
