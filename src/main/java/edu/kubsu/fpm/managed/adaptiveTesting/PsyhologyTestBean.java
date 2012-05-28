package edu.kubsu.fpm.managed.adaptiveTesting;

import edu.kubsu.fpm.DAO.AllAnswersDAO;
import edu.kubsu.fpm.DAO.TaskDAO;
import edu.kubsu.fpm.DAO.TestDAO;
import edu.kubsu.fpm.DAO.TestTypeDAO;
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
 * Date: 26.05.12
 * Time: 17:40
 */

@ManagedBean
@SessionScoped
public class PsyhologyTestBean {

    private String contentTask;
    private Test test;
    private List<Task> taskList;
    private Task currentTask;
    private int countRightAnswers = 0;      // Колличество правильных ответов
    private int totalCount = 0;             // Количество заданных вопросов
    private Map<String, Integer> questionList;   // Варианты ответов
    private Integer studentVariant = null;
    private int idRightAnswer = 0;

//        Здесь будем собирать информацию о студенте
    private Person student;
    private String representType;

    @EJB
    private TestDAO testDAO;

    @EJB
    private TestTypeDAO typeDAO;

    @EJB
    private TaskDAO taskDAO;

    @EJB
    private AllAnswersDAO allAnswersDAO;

    public PsyhologyTestBean() {
        student = (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
        String textChosen = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("textChosen");
        String imageChosen = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imageChosen");
        if (textChosen.equals("1"))
            representType = "text";
        else if (imageChosen.equals("1"))
            representType = "image";
    }

    public String getContentTask() {
        test = getCurrentTest();
        taskList = getTaskList();

        currentTask = taskList.get(totalCount);
        return currentTask.getContent();
    }

    public List<Task> getTaskList() {
        if (taskList == null)
            taskList = taskDAO.getTaskListByTest(test);
        return taskList;
    }

    private Test getCurrentTest() {
        if (test == null){
            test = testDAO.getTestByType(typeDAO.findByName("psylogical")).get(0);
        }
        return test;
    }

    public String checkStudentVariant(){
        if (studentVariant == idRightAnswer){
            countRightAnswers++;
        }
        studentVariant = null;

        if (taskList.size() != totalCount + 1){
            totalCount++;
            String url = null;
            // TODO Подумать, как организовать переход между тектстовыми и графическими задачами.
            return url;
        }
        else{
            totalCount++;
            return "";
        }
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

    public void setContentTask(String contentTask) {
        this.contentTask = contentTask;
    }

    public Integer getStudentVariant() {
        return studentVariant;
    }

    public void setStudentVariant(Integer studentVariant) {
        this.studentVariant = studentVariant;
    }

    public void setQuestionList(Map<String, Integer> questionList) {
        this.questionList = questionList;
    }
}
