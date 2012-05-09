package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.GroupDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.model.Group;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Marina
 * Date: 08.05.12
 * Time: 11:45
 */

@ManagedBean
@SessionScoped
public class StudentPS {

    private String studentId = "3";
    private List<Course_variation> variationList;

    @EJB
    private PersonDAO personDAO;

    @EJB
    private GroupDAO groupDAO;

    public StudentPS() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("studentId", studentId);
    }

    public String getStudentId() {
        Person person = personDAO.getPersonById(Integer.parseInt(studentId));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("student",person);
        return studentId;
    }

    public List<Course_variation> getVariationList() {
        if (variationList == null){
            List<Group> groups = personDAO.getGroupListByPersonId(Integer.parseInt(studentId));
            for (Group group: groups){
                if (variationList == null){
                    variationList = new ArrayList<>();
                }
                variationList.add(groupDAO.getCourseVarByGroupId(group.getId()));
            }
        }
        return variationList;
    }

    public String moveToCourse(){
        Integer courseVarId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("choseCourse"));
        Course_variation course_variation = null;
        for (Course_variation variation: variationList){
            if (variation.getId() == courseVarId){
                course_variation = variation;
                break;
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("studentCourseVar", course_variation);
        return "selected_course";
    }

    public void setVariationList(List<Course_variation> variationList) {
        this.variationList = variationList;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
