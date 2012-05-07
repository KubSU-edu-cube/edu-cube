package edu.kubsu.fpm.managed.teacher_ps;


import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.entity.*;
import edu.kubsu.fpm.model.Group;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * User: Marina
 * Date: 01.05.12
 * Time: 20:43
 */

@ManagedBean
@SessionScoped
public class EstFunctionBean {

    private Integer currentCourse;
    private Map<String, Integer> courseList;
    private Integer currentFunction;
    private Map<String, Integer> funcList;
    private String newEstFunc;
    private String newFuncMessage = "";
    private String assignFuncMessage = "";
    private List<EstimationFunc_Group> func_groupList = new ArrayList<>();   // список записей
    private Map<EstimationFuncGroupPK, Boolean> selectedIDs = new HashMap<>(); // Выбранные записи
    
    private List<EstimationFunction> functionList = new ArrayList<>();  // список существующих функций

    private int teacherId;
    
    @EJB
    private Course_variationDAO course_variationDAO;
    
    @EJB
    private PersonDAO personDAO;

    @EJB
    private EstimationFunctionDAO estimationFunctionDAO;

    @EJB
    private EstimationFunc_GroupDAO estimationFunc_groupDAO;

    @EJB
    private GroupDAO groupDAO;

    @EJB
    private CourseDAO courseDAO;

    public EstFunctionBean() {
        this.setTeacherId(Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId")));
    }

    public List<EstimationFunc_Group> getFunc_groupList() {
        return estimationFunc_groupDAO.getFunc_Group();
    }

    public List<EstimationFunction> getFunctionList() {
        return estimationFunctionDAO.getAll();
    }

    public Map<String, Integer> getCourseList() {
        courseList = new LinkedHashMap<>();
        List<Course> courses = course_variationDAO.getCourseByPerson(personDAO.getPersonById(teacherId));
        for (Course course: courses)
            courseList.put(course.getName(), course.getId());
        return courseList;
    }

    public Map<String, Integer> getFuncList() {
        funcList = new LinkedHashMap<>();
        List<EstimationFunction> estimationFunctions = estimationFunctionDAO.getAll();
        for(EstimationFunction function: estimationFunctions)
            funcList.put(function.getFunction(), function.getId());
        return funcList;
    }

    // Функция сохраняет результат сопоставления группе функции
    public void saveAssign(){
        Group group = getGroupByCourseName(currentCourse);

        List<EstimationFunc_Group> func_groupList = estimationFunc_groupDAO.getFunc_Group();
        for(EstimationFunc_Group func_group: func_groupList){
            if (func_group.getEstimationFuncGroupPK().getGroupId() == group.getId()){
                estimationFunc_groupDAO.remove(func_group.getEstimationFuncGroupPK());
                break;
            }
        }

        EstimationFunc_Group estimationFunc_group = new EstimationFunc_Group(currentFunction, group.getId());
        estimationFunc_groupDAO.persist(estimationFunc_group);
        assignFuncMessage = "Изменения успещно внесены.";
    }

    private Group getGroupByCourseName(Integer idCourse) {
        Course_variation course_variation = course_variationDAO.getCourseVarByPersonAndCourse(personDAO.getPersonById(teacherId),
                courseDAO.getCourseById(idCourse));
        return groupDAO.getGroupByCourseVar(course_variation);
    }

    public void deleteFuncGroup(){
        List<EstimationFunc_Group> selectedFuncGroupsList = getSelectedFuncGroup();
        for (EstimationFunc_Group func_group: selectedFuncGroupsList){
            estimationFunc_groupDAO.remove(func_group.getEstimationFuncGroupPK());
            func_groupList.remove(func_group);
        }
    }

    private List<EstimationFunc_Group> getSelectedFuncGroup() {
        List<EstimationFunc_Group> selectedFuncGroupList = new ArrayList<>();
        for (EstimationFuncGroupPK key: selectedIDs.keySet()){
            if (selectedIDs.get(key)){
                selectedFuncGroupList.add(estimationFunc_groupDAO.findByID(key));
                selectedIDs.remove(key);
            }
        }
        return selectedFuncGroupList;
    }

    public void saveNewFunc(){
        List<String> estimationFunctions = estimationFunctionDAO.getAllEstFunction();
        if (!estimationFunctions.contains(newEstFunc)){
            EstimationFunction estimationFunction = new EstimationFunction();
            estimationFunction.setFunction(newEstFunc);
            estimationFunctionDAO.persist(estimationFunction);
            newFuncMessage = "Функция была успешно добавлена.";
        }
        else
            newFuncMessage = "Данная функция в базе уже существует. Введите, пожалуйста, другую.";
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

    public Integer getCurrentFunction() {
        return currentFunction;
    }

    public void setCurrentFunction(Integer currentFunction) {
        this.currentFunction = currentFunction;
    }

    public String getNewEstFunc() {
        return newEstFunc;
    }

    public void setNewEstFunc(String newEstFunc) {
        this.newEstFunc = newEstFunc;
    }

    public String getNewFuncMessage() {
        return newFuncMessage;
    }

    public void setNewFuncMessage(String newFuncMessage) {
        this.newFuncMessage = newFuncMessage;
    }

    public String getAssignFuncMessage() {
        return assignFuncMessage;
    }

    public void setAssignFuncMessage(String assignFuncMessage) {
        this.assignFuncMessage = assignFuncMessage;
    }

    public Map<EstimationFuncGroupPK, Boolean> getSelectedIDs() {
        return selectedIDs;
    }

    public void setSelectedIDs(Map<EstimationFuncGroupPK, Boolean> selectedIDs) {
        this.selectedIDs = selectedIDs;
    }
}
