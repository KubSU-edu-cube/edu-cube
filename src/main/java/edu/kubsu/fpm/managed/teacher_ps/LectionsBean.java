package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Lection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 26.04.12
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class LectionsBean {

    private List<Lection> lectionVariationList;
    private Course_variation course_variation;

    public String moveToLection() {
        String chosenLection =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("chosenLection");
        List<Lection> lections =
                (List<Lection>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lectionVariationList");
        for (Lection lection : lections) {
            if (lection.getId() == Integer.parseInt(chosenLection)) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lectionVariation", lection);
                break;
            }

        }
        return "lection";
    }

    public List<Lection> getLectionVariationList() {

        lectionVariationList =
                (List<Lection>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lectionVariationList");
        return lectionVariationList;
    }

    public void setLectionVariationList(List<Lection> lectionVariationList) {
        this.lectionVariationList = lectionVariationList;
    }

    public Course_variation getCourse_variation() {
        course_variation =
                (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("courseVariation");
        return course_variation;
    }

    public void setCourse_variation(Course_variation course_variation) {
        this.course_variation = course_variation;
    }
}
