package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.Course_variationDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Course;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Person;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 25.04.12
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class CourseCreation {
    private String courseName;
    private String courseDescription;
    private String hoursCount;
    private String level;

    @EJB
    private Course_variationDAO course_variationDAO;
    @EJB
    private PersonDAO personDAO;

    public String createCourse(){
        Course course = new Course();
        course.setName(this.courseName);

        Course_variation course_variation = new Course_variation();

        course_variation.setDescription(this.courseDescription);
        course_variation.setDuration(this.hoursCount);
        course_variation.setLevel(this.level);

        int i = Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId"));
        Person person = personDAO.getPersonById(i);

        course_variation.setPerson(person);
        course_variation.setCourse(course);

        course_variationDAO.persist(course_variation);

        return "personal_information";
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getHoursCount() {
        return hoursCount;
    }

    public void setHoursCount(String hoursCount) {
        this.hoursCount = hoursCount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
