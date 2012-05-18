package edu.kubsu.fpm.servlet;

import edu.kubsu.fpm.ejb.DBImageLocal;
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
 * @author Zhulanova Anna
 * Сервлет для отображения бинарных каринок
 * Date: 26.04.11
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "DBImageServlet", urlPatterns = "/DBImageServlet")
public class DBImageServlet extends HttpServlet {
    @EJB
    private DBImageLocal DBImage;
    private byte[] img;

    /**
     * в результате выполнения DBImageServlet в окне браузера отобразится одна из картинок,
     * содержащаяся в {@link edu.kubsu.fpm.ejb.DBImageLocal#getImgList()}
     * @param request  должен содержать параметр imgcount - номер элемента списка(н.р. ...?imgcount=1)
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String personId = request.getParameter("personId");
        String mainPersonid = request.getParameter("mainPersonid");
        if(personId!=null){
            for (PersonalPhoto personalPhoto:DBImage.getSmallImgs()){
                if(personalPhoto.getPersonId()==Integer.parseInt(personId)){
                    img = personalPhoto.getContent();
                    break;
                }
            }
        }
        if(mainPersonid!=null){
            img = DBImage.getMainPhoto().getContent();
        }

//        String count = request.getParameter("imgcount");  // получаем параметр запроса - номер картинки в лекции
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
//        byte[] img = DBImage.getImgList().get(Integer.parseInt(count));
        os.write(img);
        os.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
