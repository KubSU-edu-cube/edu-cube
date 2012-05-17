package edu.kubsu.fpm.managed.teacher_ps;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 25.04.12
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class CoursesBean {
    private String teacherId;

    public CoursesBean() {
        this.setTeacherId((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId"));
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
