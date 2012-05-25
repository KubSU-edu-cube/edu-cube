package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.*;
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
 * Date: 25.05.12
 * Time: 13:01
 */

@ManagedBean
@SessionScoped
public class CreativeTasksBean {

    private List<StudentAnswer> unckeckedTestList;
    private Person teacher;
    private String taskMessage;

    public CreativeTasksBean() {
        teacher = (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("person");
    }

    @EJB
    private LectionDAO lectionDAO;

    @EJB
    private TestDAO testDAO;

    @EJB
    private StudentAnswerDAO answerDAO;

    @EJB
    private TaskDAO taskDAO;

    @EJB
    private MarkDAO markDAO;

    @EJB
    private Course_variationDAO variationDAO;

    public String moveToTask(){
        Integer answerId = Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("choosenTask"));
        StudentAnswer answer = answerDAO.findById(answerId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("studentAnswer", answer);
        return "check_task";
    }

    public List<StudentAnswer> getUnckeckedTestList() {
        unckeckedTestList = new ArrayList<StudentAnswer>();
        List<StudentAnswer> answerList = answerDAO.getAll();

        List<Course_variation> course_variationList = variationDAO.findByPersonId(teacher.getId());
        
//        Среди всех курсов для преподавателя
        for(Course_variation variation: course_variationList){
            List<Lection> lectionList = lectionDAO.findLectionsByCourseVarId(variation.getId());

//            И среди всех лекций для данного курса
            for(Lection lection: lectionList){
                List<Test> testList = testDAO.getTestListByLectionId(lection);

//                Находим такие тестовые задания, на которые студент дал ответ, но он еще не проверен.
                for (Test test: testList){
                    if (test.getType().getType().equals("checked")){
                        Task task = taskDAO.getTaskListByTest(test).get(0);
                        for(StudentAnswer answer: answerList){
                            if (answer.getTask().getId() == task.getId()){
//                                Проверяем, чтобы оценка еще не была посталена
                                Mark mark = markDAO.getMarkByTest(test);

                                if (mark.getId() == null){
                                    unckeckedTestList.add(answer);
                                }
                            }
                        }
                    }
                }
            }
        }
        return unckeckedTestList;
    }

    public void setUnckeckedTestList(List<StudentAnswer> unckeckedTestList) {
        this.unckeckedTestList = unckeckedTestList;
    }

    public String getTaskMessage() {
        if (taskMessage == null){
            taskMessage = "Непроверенные задания";
        }
        else {
            if (unckeckedTestList.size() > 0)
                taskMessage = "Непроверенные задания";
            else
                taskMessage = "Все задния уже проверены.";
        }
        return taskMessage;
    }

    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }
}
