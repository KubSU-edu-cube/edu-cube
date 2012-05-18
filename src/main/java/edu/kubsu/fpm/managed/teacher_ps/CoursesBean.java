package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.entity.Course_variation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

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
    public String moveToCourse(){
        String courseId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("chosenCourse");
        Course_variation course_variation=null;
        List<Course_variation> variationList =
                (List<Course_variation>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("courseVariationList");
        for (Course_variation cv: variationList){
            if(cv.getId()==Integer.parseInt(courseId)){
                course_variation = cv;
                break;
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("courseVariation",course_variation);
        return "course";
    }
}
