package edu.kubsu.fpm.managed.teacher_ps;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 26.04.12
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class CourseBean {

    public String moveToLections(){

//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("")
        return "lections";
    }
}
