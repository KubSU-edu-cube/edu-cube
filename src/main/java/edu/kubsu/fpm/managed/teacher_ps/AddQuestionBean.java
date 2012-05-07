package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.entity.Course;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.model.AdditionalQuestion;
import edu.kubsu.fpm.model.Group;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * User: Marina
 * Date: 04.05.12
 * Time: 9:14
 */

@ManagedBean
@SessionScoped
public class AddQuestionBean {

    private Integer obligPercent;
    private Integer rightAnswerPercent;
    private Integer addPercent;
    private Integer currentCourse;
    private Map<String, Integer> courseList;
    private int teacherId;
    private String assignFuncMessage = "";
    private List<AdditionalQuestion> additionalQuestionList = new ArrayList<>();
    private Map<Integer, Boolean> selectedIDs = new HashMap<>(); // Выбранные записи

    @EJB
    private Course_variationDAO course_variationDAO;

    @EJB
    private PersonDAO personDAO;
    
    @EJB
    private CourseDAO courseDAO;
    
    @EJB
    private GroupDAO groupDAO;

    @EJB
    private AdditionalQuestionDAO additionalQuestionDAO;

    public AddQuestionBean() {
        this.setTeacherId(Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId")));
    }

    public List<AdditionalQuestion> getAdditionalQuestionList() {
        return additionalQuestionDAO.getAll();
    }

    public Map<String, Integer> getCourseList() {
        courseList = new LinkedHashMap<>();
        List<Course> courses = course_variationDAO.getCourseByPerson(personDAO.getPersonById(teacherId));
        for (Course course: courses)
            courseList.put(course.getName(), course.getId());
        return courseList;
    }

    public void deleteAddQuestions(){
        List<AdditionalQuestion> selectedFuncGroupsList = getSelectedFuncGroup();
        for (AdditionalQuestion additionalQuestion: selectedFuncGroupsList){
            additionalQuestionDAO.remove(additionalQuestion.getId());
            additionalQuestionList.remove(additionalQuestion);
        }
    }

    private List<AdditionalQuestion> getSelectedFuncGroup() {
        List<AdditionalQuestion> selectedAddQuest = new ArrayList<>();
        for (Integer key: selectedIDs.keySet()){
            if (selectedIDs.get(key)){
                selectedAddQuest.add(additionalQuestionDAO.findByID(key));
                selectedIDs.remove(key);
            }
        }
        return selectedAddQuest;
    }

    public void savePercent(){
        Group group = getGroupByCourseName(currentCourse);
        boolean alreadyExists = false;

        List<AdditionalQuestion> addQuestionList = additionalQuestionDAO.getAll();
        for (AdditionalQuestion addQuestion: addQuestionList){
            if ((addQuestion.getGroupid().getId().equals(group.getId()))&&(addQuestion.getPercentObligatoryQuestion().equals(obligPercent))&&
                    (addQuestion.getPercentRigthAnswers() == rightAnswerPercent)){
                if (addQuestion.getPercentAdditionalQuestion().equals(addPercent)){
                    assignFuncMessage = "Данная запись уже существует в базе.";
                    alreadyExists = true;
                    break;
                }
                else {
                    additionalQuestionDAO.remove(addQuestion.getId());
                    break;
                }
            }
        }

        if (!alreadyExists){
            AdditionalQuestion addQuestion = new AdditionalQuestion(obligPercent, rightAnswerPercent, addPercent, group);   //obligPercent, rightAnswerPercent, addPercent, group.getId()
            additionalQuestionDAO.persist(addQuestion);
            assignFuncMessage = "Изменения успещно внесены.";
        }
        obligPercent = null;
        rightAnswerPercent = null;
        addPercent = null;
    }

    private Group getGroupByCourseName(Integer idCourse) {
        Course_variation course_variation = course_variationDAO.getCourseVarByPersonAndCourse(personDAO.getPersonById(teacherId),
                courseDAO.getCourseById(idCourse));
        return groupDAO.getGroupByCourseVar(course_variation);
    }

    public Integer getObligPercent() {
        return obligPercent;
    }

    public void setObligPercent(Integer obligPercent) {
        this.obligPercent = obligPercent;
    }

    public Integer getRightAnswerPercent() {
        return rightAnswerPercent;
    }

    public void setRightAnswerPercent(Integer rightAnswerPercent) {
        this.rightAnswerPercent = rightAnswerPercent;
    }

    public Integer getAddPercent() {
        return addPercent;
    }

    public void setAddPercent(Integer addPercent) {
        this.addPercent = addPercent;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Integer currentCourse) {
        this.currentCourse = currentCourse;
    }

    public String getAssignFuncMessage() {
        return assignFuncMessage;
    }

    public void setAssignFuncMessage(String assignFuncMessage) {
        this.assignFuncMessage = assignFuncMessage;
    }

    public Map<Integer, Boolean> getSelectedIDs() {
        return selectedIDs;
    }

    public void setSelectedIDs(Map<Integer, Boolean> selectedIDs) {
        this.selectedIDs = selectedIDs;
    }
}
