package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.AllAnswersDAO;
import edu.kubsu.fpm.DAO.StudentAnswerDAO;
import edu.kubsu.fpm.DAO.TaskDAO;
import edu.kubsu.fpm.entity.Answer;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.entity.Task;
import edu.kubsu.fpm.entity.Test;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * User: Marina
 * Date: 09.05.12
 * Time: 17:28
 */

@ManagedBean
@SessionScoped
public class CreationBaseTest {

    private Test test;
    private String contentTask;
    private String studentAnswer;
    private Person student;
    private Task currentTask;
    private int countRightAnswers = 0;      // Колличество правильных ответов
    private int totalCount = 0;             // Колличество всего вопросов.
    private List<Task> taskList;
    private Map<String, Integer> questionList;   // Варианты ответов
    private int idRightAnswer = 0;
    private Integer studentVariant = null;

    @EJB
    private TaskDAO taskDAO;

    @EJB
    private StudentAnswerDAO studentAnswerDAO;

    @EJB
    private AllAnswersDAO allAnswersDAO;

    public CreationBaseTest() {
        test = (Test) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("test");
        student = (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
    }

    public Map<String, Integer> getQuestionList() {
        questionList = new HashMap<>();
        List<Answer> answerList = allAnswersDAO.getAnswersByTask(currentTask);
        Random r = new Random();
        boolean[] wasHere = new boolean[answerList.size()];
        for (int i = 0; i < answerList.size(); i++){
            wasHere[i] = false;
        }
        boolean t = true;
        while (t){
            int id = r.nextInt(answerList.size());
            if (!wasHere[id]){
                questionList.put(answerList.get(id).getContent(), answerList.get(id).getId());
                if (answerList.get(id).getRight()){
                    idRightAnswer = answerList.get(id).getId();
                }
                wasHere[id] = true;
                
                // Проверяем везде ли стоят флажки true
                boolean theEnd = true;
                for (int i = 0; i <answerList.size(); i++){
                    if (!wasHere[i]){
                        theEnd = false;
                        break;
                    }
                }
                if (theEnd)
                    t = false;
            }
        }
        return questionList;
    }

    public void checkStudentVariant(){
        if (studentVariant == idRightAnswer){
            countRightAnswers++;
        }
        studentVariant = null;
    }

    public List<Task> getTaskList() {
        return taskDAO.getTaskListByTest(test);
    }

    public void checkAnswer(){
        List<Answer> answerList = allAnswersDAO.getAnswersByTask(currentTask);
        for (Answer answer : answerList){
            if ((answer.getRight())&&(answer.getContent().equals(studentAnswer))){
                countRightAnswers++;
            }
        }
        studentAnswer = null;
    }

    public String moveToNextQuestion(){
        if (taskList.size() != totalCount + 1){
            totalCount++;
            String url = null;

            if (taskList.get(totalCount).getTaskType().getTaskType().equals("input"))
                url = "base_test";
            else if (taskList.get(totalCount).getTaskType().getTaskType().equals("check"))
                url = "check_test";
            return url;
        }
        else{
            totalCount++;
            return "result_of_test";
        }
    }

    public String getContentTask() {
        if (taskList == null)
            taskList = getTaskList();
        currentTask = taskList.get(totalCount);
        return currentTask.getContent();
    }

    public void setContentTask(String contentTask) {
        this.contentTask = contentTask;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getStudentVariant() {
        return studentVariant;
    }

    public void setStudentVariant(Integer studentVariant) {
        this.studentVariant = studentVariant;
    }

    public int getCountRightAnswers() {
        return countRightAnswers;
    }

    public void setCountRightAnswers(int countRightAnswers) {
        this.countRightAnswers = countRightAnswers;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
