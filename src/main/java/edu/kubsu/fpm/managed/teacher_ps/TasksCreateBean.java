package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.entity.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 9:34
 */

@ManagedBean
@SessionScoped
public class TasksCreateBean {
    
    private int testType = 1;
    private Integer currentLection;
    private Map<String, Integer> lectionList;
    private String taskText;
    private List<Task> creativeTaskList;     // Список тестов для выбранной лекции
    private Map<Integer, Boolean> selectedIDs = new HashMap<>(); // Выбранные записи

    private int teacherId;
    Course_variation course_variation = new Course_variation();

    private String testName;        // Имя создаваемого теста

    @EJB
    private Course_variationDAO course_variationDAO;

    @EJB
    private PersonDAO personDAO;

    @EJB
    private LectionDAO lectionDAO;

    @EJB
    private TestTypeDAO testTypeDAO;

    @EJB
    private TestDAO testDAO;

    @EJB
    private TaskTypeDAO taskTypeDAO;

    @EJB
    private TaskDAO taskDAO;

    public List<Task> getCreativeTaskList() {
        creativeTaskList = new ArrayList<>();
        if (currentLection != null){
            List<Test> tests = testDAO.getTestListByLectionId(lectionDAO.findById(currentLection));
            for (Test test: tests){
                if (testTypeDAO.findByName("checked").getId()==test.getType().getId()){
                    List<Task> tasks = taskDAO.getTaskListByTest(test);
                    for(Task task: tasks)
                        creativeTaskList.add(task);
                }
            }
        }
        return creativeTaskList;
    }

    public TasksCreateBean() {
        this.setTeacherId(Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId")));
    }

    public void deleteCreativeTask(){
        List<Test> selectedTests = getSelectedTask();
        for (Test selectedTest: selectedTests){
            taskDAO.remove(selectedTest.getId());
            creativeTaskList.remove(selectedTest);
        }
    }

    private List<Test> getSelectedTask() {
        List<Test> testList = new ArrayList<>();
        for(Integer key: selectedIDs.keySet()){
            if (selectedIDs.get(key)){
                testList.add(testDAO.findById(key));
                selectedIDs.remove(key);
            }
        }
        return testList;
    }

    public Map<String, Integer> getLectionList() {
        lectionList = new LinkedHashMap<>();
        course_variation = course_variationDAO.getCourseVarById(1); // TODO В courses.xhtml (CoursesBean) неправильно загружается в FacesContext courseVariation
        List<Lection> lections = course_variationDAO.getListLectionById(course_variation.getId());
        if (lections.size() > 0){
            for (Lection lection: lections)
                lectionList.put(lection.getName(), lection.getId());
        }
        return lectionList;
    }

    public void saveCheckedTask(){
        Test test = new Test();
        test.setLection(lectionDAO.findById(currentLection));
        test.setType(testTypeDAO.findByName("checked"));
        testDAO.persist(test);

        Task task = new Task();
        task.setContent(taskText);
        task.setTaskType(taskTypeDAO.findByType("creative"));
        task.setTest(test);
        taskDAO.persist(task);
        taskText = null;
    }

    // TODO
//    * Реализовать страницу создания тестов. А так же страницу их удаления.


//    private void CheckExistenceCheckedTest(Lection lection, TestType type) {
//        List<Test> testList = testDAO.getAll();
//        for(Test test: testList){
//            if (test.getType() == type)
//                testDAO.remove(test.getId());
//        }
//    }

    public String checkTestType(){
        if (testType == 1)
            return "test_creation";
        else
            return "task_creation";
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public Integer getCurrentLection() {
        return currentLection;
    }

    public void setCurrentLection(Integer currentLection) {
        this.currentLection = currentLection;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Map<Integer, Boolean> getSelectedIDs() {
        return selectedIDs;
    }

    public void setSelectedIDs(Map<Integer, Boolean> selectedIDs) {
        this.selectedIDs = selectedIDs;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
