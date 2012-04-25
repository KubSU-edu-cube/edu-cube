package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.managed.teacher_ps.CoursesBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 25.04.12
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class teacherPS {
    private String teacherId = "1";

    public teacherPS() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("teacherId",teacherId);
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
