package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.StudentAnswerDAO;
import edu.kubsu.fpm.DAO.TaskDAO;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.entity.StudentAnswer;
import edu.kubsu.fpm.entity.Task;
import edu.kubsu.fpm.entity.Test;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * User: Marina
 * Date: 09.05.12
 * Time: 16:10
 */
@ManagedBean
@SessionScoped
public class CreationCreativeTest {

    private Test test;
    private String contentTask;
    private String studentAnswer;
    private Person student;
    private Task currentTask;

    @EJB
    private TaskDAO taskDAO;

    @EJB
    private StudentAnswerDAO studentAnswerDAO;

    public CreationCreativeTest() {
        test = (Test) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("creativeTest");
        student = (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
    }

    public void saveAnswer(){
        StudentAnswer answer;
        try{
            answer = studentAnswerDAO.getAnswerByTaskAndPerson(student, currentTask);
        } catch (Exception e){
            answer = new StudentAnswer();
            answer.setContent(studentAnswer);
            answer.setPerson(student);
            answer.setTask(currentTask);
            studentAnswerDAO.persist(answer);
        }
    }

    public String getContentTask(){
        List<Task> taskList = taskDAO.getTaskListByTest(test);
        currentTask = taskList.get(0);
        return currentTask.getContent();
    }

    public void setContentTask(String contentTask) {
        this.contentTask = contentTask;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
}
