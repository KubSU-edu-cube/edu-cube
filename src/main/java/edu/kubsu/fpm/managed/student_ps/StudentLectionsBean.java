package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.LectionDAO;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Lection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * User: Marina
 * Date: 10.05.12
 * Time: 8:26
 */

@ManagedBean
@SessionScoped
public class StudentLectionsBean {

    private Course_variation variation;
    private List<Lection> lectionList;
    
    @EJB
    private LectionDAO lectionDAO;

    public StudentLectionsBean() {
        this.variation = (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("studentCourseVar");
    }

    public List<Lection> getLectionList() {
        return lectionDAO.findLectionsByCourseVarId(variation.getId());
    }

    public String moveToLection(){
        Integer lectionId = Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lectionId"));
        Lection lection = lectionDAO.findById(lectionId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("studentLection", lection);
        return "lection";
    }

    public Course_variation getVariation() {
        return variation;
    }

    public void setVariation(Course_variation variation) {
        this.variation = variation;
    }

    public void setLectionList(List<Lection> lectionList) {
        this.lectionList = lectionList;
    }
}
