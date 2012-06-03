package edu.kubsu.fpm.managed.adaptiveTesting;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.entity.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.*;

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
    private int countQuestion = 0;             // Количество заданных вопросов
    private Map<String, Integer> questionList;   // Варианты ответов
    private Integer studentVariant = null;
    private int idRightAnswer = 0;
    private Long beginTime;
    private String currentPage;

    private int TIME_OUT = 300000; // 5 минут в милисекундах

//        Здесь будем собирать информацию о студенте
    private Person student;
    private String representType;
    private int totalCount = 0;

    @EJB
    private TestDAO testDAO;

    @EJB
    private TestTypeDAO typeDAO;

    @EJB
    private TaskDAO taskDAO;

    @EJB
    private AllAnswersDAO allAnswersDAO;
    
    @EJB
    private TaskTypeDAO taskTypeDAO;

    public PsyhologyTestBean() {
        beginTime = System.currentTimeMillis();

        student = (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
        String textChosen = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("textChosen");
        String imageChosen = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imageChosen");
        if (textChosen.equals("1")){
            representType = "text";
            currentPage = "text_tasks";
        }
        else if (imageChosen.equals("2")){
            representType = "image";
            currentPage = "image_tasks";
        }
    }

    public String getContentTask() {
        test = getCurrentTest();
        taskList = getTaskList(taskTypeDAO.findByType("text"));

        currentTask = taskList.get(countQuestion);
        return currentTask.getContent();
    }

    public List<Task> getTaskList(TaskType taskType) {
        if (taskList == null)
            taskList = taskDAO.getTaskListByTestAndTaskType(test, taskType);
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

//        Если кончились вопросы или кончилось время
        if ((countQuestion + 1 == taskList.size())||(System.currentTimeMillis() - beginTime > TIME_OUT)){
            totalCount += countQuestion;    // Накапливаем общее число вопросов
            countQuestion = 0;
            if (representType.equals("text"))
                currentPage = "image_tasks";
            else
                currentPage = "text_tasks";
        }
        else {
//            Иначе остаемся на этой же странице.
            countQuestion++;
        }
        return currentPage;
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
