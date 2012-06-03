package edu.kubsu.fpm.managed.teacher_ps;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 26.04.12
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class LectionPDF {
    private String content;

    public String getContent() {
        Object lectionId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lectionToShowId");
        int id = (Integer)lectionId;
        String s = "          <object width=\"840\" height=\"500\" type=\"application/pdf\" data=\"http://localhost:8080/educube-1.0/DBLectionServlet?lectionId="+id+"\" id=\"pdf_content\">\n" +
                "                    <p>Insert your error message here, if the PDF cannot be displayed.</p>\n" +
                "                </object>";
        return s;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
