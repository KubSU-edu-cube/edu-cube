package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.entity.Lection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * User: Marina
 * Date: 10.05.12
 * Time: 8:45
 */

@ManagedBean
@SessionScoped
public class StudentLectionBean {
    
    private Lection currentLection;

    public StudentLectionBean() {
        this.currentLection = (Lection) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("studentLection");
    }

    public Lection getCurrentLection() {
        return currentLection;
    }

    public void setCurrentLection(Lection currentLection) {
        this.currentLection = currentLection;
    }
}
