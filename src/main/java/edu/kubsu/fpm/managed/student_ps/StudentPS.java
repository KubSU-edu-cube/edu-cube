package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Person;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * User: Marina
 * Date: 08.05.12
 * Time: 11:45
 */

@ManagedBean
@SessionScoped
public class StudentPS {

    private String studentId = "3";

    @EJB
    private PersonDAO personDAO;

    public StudentPS() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("studentId", studentId);
    }

    public String getStudentId() {
        Person person = personDAO.getPersonById(Integer.parseInt(studentId));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("person",person);
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
