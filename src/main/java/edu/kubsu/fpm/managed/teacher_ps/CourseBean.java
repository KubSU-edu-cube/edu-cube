package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.LectionDAO;
import edu.kubsu.fpm.ejb.DBLectionLocal;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Lection;
import edu.kubsu.fpm.managed.teacher_ps.classes.CourseLection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
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
public class CourseBean {

    private Course_variation course_variation;

    @EJB
    private LectionDAO lectionDAO;
    @EJB
    private DBLectionLocal lectionLocal;

    public String moveToLections(){
        // какая текущая версия курса?
        Course_variation course_variation =
                (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("courseVariation");
        // какие лекции сюда относятся?
        List<Lection> lections = lectionDAO.findLectionsByCourseVarId(course_variation.getId());
        // складываем лекции по данному курсу контексту
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lectionVariationList",lections);
        // складываем контент лекций сервлету
        List<CourseLection> courseLections = new ArrayList<CourseLection>();
        for(Lection lection: lections){
            CourseLection courseLection = new CourseLection();
            courseLection.setId(lection.getId());
            courseLection.setContent(lection.getContent());
            courseLections.add(courseLection);
        }
        lectionLocal.setLections(courseLections);
        return "lections";
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
