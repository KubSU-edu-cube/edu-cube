package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.entity.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private int teacherId;
    Course_variation course_variation = new Course_variation();

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

    public TasksCreateBean() {
        this.setTeacherId(Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId")));
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
    }

    // TODO
//    * Сделать отображение уже назначенных творческих заданий и их удаление.
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

}
