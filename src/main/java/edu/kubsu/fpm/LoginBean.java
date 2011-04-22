package edu.kubsu.fpm;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class LoginBean implements Serializable {

    private static final long serialVersionUID = -2403138958014741653L;

    public LoginBean() {
        System.out.println("post construct: initialize");
    }

    public String logout(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            response.sendRedirect("index.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
