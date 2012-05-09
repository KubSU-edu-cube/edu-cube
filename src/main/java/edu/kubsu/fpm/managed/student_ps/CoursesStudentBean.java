package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.Course_variationDAO;
import edu.kubsu.fpm.DAO.GroupDAO;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.model.Group;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Marina
 * Date: 08.05.12
 * Time: 14:33
 */
@ManagedBean
@SessionScoped
public class CoursesStudentBean {

    private Person student;
    private String requiredCourseName;
    private String teacherName;
    private List<Course_variation> course_variationList;
    private Map<Integer, Boolean> selectedIDs = new HashMap<>(); // Выбранные записи

    @EJB
    private Course_variationDAO course_variationDAO;

    @EJB
    private GroupDAO groupDAO;

    public CoursesStudentBean() {
        this.setStudent((Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student"));
    }

    public void serchCourses(){
        course_variationList = new ArrayList<>();
        if ((requiredCourseName.trim().length() == 0)&&(teacherName.trim().length() == 0)){
            course_variationList = course_variationDAO.getAll();
        }
        else if ((requiredCourseName.trim().length() != 0)&&(teacherName.trim().length() != 0)){
            getCourseByNameAndTeacher(requiredCourseName, teacherName);
        }
        else if (requiredCourseName.trim().length() != 0){
            getCoursesByName(requiredCourseName);
        }
        else {
            getCoursesByTeacher(teacherName);
        }
    }

    private void getCoursesByTeacher(String teacherName) {
        List<Course_variation> course_variations = course_variationDAO.getAll();
        for (Course_variation variation: course_variations){
            if (checkPerson(variation.getPerson(), teacherName)){
                course_variationList.add(variation);
            }
        }
    }

    private void getCoursesByName(String courseName) {
        List<Course_variation> course_variations = course_variationDAO.getAll();
        for (Course_variation variation: course_variations){
            if (variation.getCourse().getName().equals(courseName)){
                course_variationList.add(variation);
            }
        }
    }

    private void getCourseByNameAndTeacher(String courseName, String teacherFIO) {
        List<Course_variation> course_variations = course_variationDAO.getAll();
        for (Course_variation variation: course_variations){
            if ((variation.getCourse().getName().equals(courseName))&&checkPerson(variation.getPerson(), teacherFIO)){
                course_variationList.add(variation);
            }
        }
    }

    private boolean checkPerson(Person person, String teacherFIO) {   // TODO Check!
        boolean personConcidence = true;
        String[] fio = teacherFIO.split(" ");
        for (int i = 0; i < fio.length; i++){
            switch (i){
                case 0:
                    if (!fio[i].equals(person.getSurname()))
                        personConcidence = false;
                    break;
                case 1:
                    if (!fio[i].equals(person.getName()))
                        personConcidence = false;
                    break;
                case 2:
                    if (!fio[i].equals(person.getPatronymic()))
                        personConcidence = false;
                    break;
            }
        }
        return personConcidence;
    }

    public String showCourses(){
        requiredCourseName = null;
        teacherName = null;
        return "search_course";
    }

    public void takeCourse(){
        List<Person> personList = new ArrayList<>();
        personList.add(student);

        List<Course_variation> course_variations = getSelectedItems();
        for (Course_variation course_variation: course_variations){
            Group group;
            try{
                group = groupDAO.getGroupByCourseVar(course_variation);
            } catch (Exception e){
                group = new Group();
                group.setCourseVariation(course_variation);
            }
            group.setStudents(personList);
            groupDAO.persist(group);
        }
    }

    private List<Course_variation> getSelectedItems() {
        List<Course_variation> course_variations = new ArrayList<>();
        for (Integer key: selectedIDs.keySet()){
            if (selectedIDs.get(key)){
                course_variations.add(course_variationDAO.getCourseVarById(key));
            }
        }
        return course_variations;
    }

    public List<Course_variation> getCourse_variationList() {
        return course_variationList;
    }

    public void setCourse_variationList(List<Course_variation> course_variationList) {
        this.course_variationList = course_variationList;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRequiredCourseName() {
        return requiredCourseName;
    }

    public void setRequiredCourseName(String requiredCourseName) {
        this.requiredCourseName = requiredCourseName;
    }

    public Map<Integer, Boolean> getSelectedIDs() {
        return selectedIDs;
    }

    public void setSelectedIDs(Map<Integer, Boolean> selectedIDs) {
        this.selectedIDs = selectedIDs;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }
}
