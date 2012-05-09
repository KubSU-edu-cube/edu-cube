package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.LectionDAO;
import edu.kubsu.fpm.DAO.TestDAO;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Lection;
import edu.kubsu.fpm.entity.Test;

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
    
    @EJB
    private LectionDAO lectionDAO;

    @EJB
    private TestDAO testDAO;

    public TestsBean() {
        this.course_variation = (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("studentCourseVar");
    }

    public Course_variation getCourse_variation() {
        return course_variation;
    }

    public void setCourse_variation(Course_variation course_variation) {
        this.course_variation = course_variation;
    }

    public List<Test> getTestList() {
        if (testList == null){
            testList = new ArrayList<>();
            testList = getTestListByType("base", testList);
        }
        return testList;
    }

    private List<Test> getTestListByType(String type, List<Test> resultList) {  // TODO В результирующий список должны входить только те тестовые задания, на кот. студент еще не дал ответ.
        //        Получаем список всех лекций для данного курса.
        List<Lection> lectionList = lectionDAO.findLectionsByCourseVarId(course_variation.getId());
        for (Lection lection : lectionList){
            List<Test> tests = testDAO.getTestListByLectionId(lection);
            for (Test test: tests){
                if (test.getType().getType().equals(type)){
                    resultList.add(test);
                }
            }
        }
        return resultList;
    }

    public List<Test> getCreativeTestList() {
        if (creativeTestList == null){
            creativeTestList = new ArrayList<>();
            creativeTestList = getTestListByType("checked", creativeTestList);
        }
        return creativeTestList;
    }

    public String moveToCreativeTest(){
        Integer testID = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("choosenCreativeTest"));
        Test test = testDAO.findById(testID);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("creativeTest", test);
        return "creative_test";
    }

    public String moveToTest(){
        Integer testID = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("choosenTest"));
        Test test = testDAO.findById(testID);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("test", test);
        return "base_test";
    }

    public void setCreativeTestList(List<Test> creativeTestList) {
        this.creativeTestList = creativeTestList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }
}
