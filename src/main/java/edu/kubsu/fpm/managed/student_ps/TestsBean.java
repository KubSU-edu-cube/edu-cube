package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.LectionDAO;
import edu.kubsu.fpm.DAO.MarkDAO;
import edu.kubsu.fpm.DAO.TaskDAO;
import edu.kubsu.fpm.DAO.TestDAO;
import edu.kubsu.fpm.entity.*;
import edu.kubsu.fpm.model.Mark;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Marina
 * Date: 09.05.12
 * Time: 10:37
 */

@ManagedBean
@SessionScoped
public class TestsBean {

    private Course_variation course_variation;      // курс, для кот. и будут строится уже созданные тесты.
    private List<Test> testList;
    private List<Test> creativeTestList;
    private Person student;
    
    @EJB
    private LectionDAO lectionDAO;

    @EJB
    private TestDAO testDAO;
    
    @EJB
    private TaskDAO taskDAO;
    
    @EJB
    private MarkDAO markDAO;

    public TestsBean() {
        this.course_variation = (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("studentCourseVar");
        this.student = (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
    }

    public List<Test> getTestList() {
        testList = new ArrayList<>();
        testList = getTestListByType("base", testList);
        return testList;
    }
    // TODO Выдавать только те задания, по кот. еще нет оценки
    private List<Test> getTestListByType(String type, List<Test> resultList) {  // TODO В результирующий список должны входить только те тестовые задания, на кот. студент еще не дал ответ.
        //        Получаем список всех лекций для данного курса.
        List<Lection> lectionList = lectionDAO.findLectionsByCourseVarId(course_variation.getId());
        for (Lection lection : lectionList){
            List<Test> tests = testDAO.getTestListByLectionId(lection);
            List<Mark> markList = markDAO.getMarkListByPerson(student);
            for (Test test: tests){
                if ((testNotComplete(test, markList))&&(test.getType().getType().equals(type))){
                    resultList.add(test);
                }
            }
        }
        return resultList;
    }

    private boolean testNotComplete(Test test, List<Mark> markList) {
        boolean notComplete = true;
        for (Mark mark: markList){
            if (mark.getTest().getId() == test.getId()){
                notComplete = false;
                break;
            }
        }
        return notComplete;
    }

    public List<Test> getCreativeTestList() {
        creativeTestList = new ArrayList<>();
        creativeTestList = getTestListByType("checked", creativeTestList);
        return creativeTestList;
    }

    public String moveToCreativeTest(){
        Integer testID = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("choosenCreativeTest"));
        Test test = testDAO.findById(testID);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("creativeTest", test);
        return "creative_test";
    }

    public String moveToTest(){
        String url = null;
        Integer testID = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("choosenTest"));
        Test test = testDAO.findById(testID);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("test", test);
        List<Task> taskList = taskDAO.getTaskListByTest(test);

        if (taskList.get(0).getTaskType().getTaskType().equals("input"))
            url = "base_test";
        else if (taskList.get(0).getTaskType().getTaskType().equals("check"))
            url = "check_test";
        return url;
    }

    public void setCreativeTestList(List<Test> creativeTestList) {
        this.creativeTestList = creativeTestList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public Course_variation getCourse_variation() {
        return course_variation;
    }

    public void setCourse_variation(Course_variation course_variation) {
        this.course_variation = course_variation;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }
}