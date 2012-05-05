package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.Course_variationDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.managed.teacher_ps.CoursesBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

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
    private List<Course_variation> variationList;
    @EJB
    private Course_variationDAO variationDAO;
    @EJB
    private PersonDAO personDAO;

    public List<Course_variation> getVariationList() {
        if(variationList==null){
            variationList = variationDAO.findByPersonId(Integer.parseInt(this.teacherId));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("courseVariationList",variationList);
        }
        return variationList;
    }

    public void setVariationList(List<Course_variation> variationList) {
        this.variationList = variationList;
    }

    public teacherPS() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("teacherId",teacherId);
    }

    public String getTeacherId() {
        Person person = personDAO.getPersonById(Integer.parseInt(teacherId));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("person",person);
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
