package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.entity.*;
import edu.kubsu.fpm.model.Group;
import edu.kubsu.fpm.model.Mark;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

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
    private Double mark;

    @EJB
    private TaskDAO taskDAO;

    @EJB
    private StudentAnswerDAO studentAnswerDAO;

    @EJB
    private AllAnswersDAO allAnswersDAO;

    @EJB
    private GroupDAO groupDAO;

    @EJB
    private EstimationFunc_GroupDAO func_groupDAO;

    @EJB
    private MarkDAO markDAO;

    public CreationBaseTest() {
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
        if (test == null){
            test = (Test) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("test");
        }
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

    public Double getMark() {
        String function = getFunctionForGroup();
        JexlEngine jexl = new JexlEngine();
        Expression func = jexl.createExpression(function);
        MapContext mapContext = new MapContext();
        mapContext.set("x", (countRightAnswers * 100 / totalCount)/100);
        Double mark = (Double) func.evaluate(mapContext);
        mark = mark > 5 ? 5 : mark;

//        Сохраняем оценку
        Mark savedMark = new Mark();
        savedMark.setMark(mark);
        savedMark.setStudent(student);
        savedMark.setTest(test);
        savedMark.setLection(test.getLection());
        markDAO.persist(savedMark);

        initParam();
        return mark;
    }

    private void initParam() {
        contentTask = null;
        studentAnswer = null;
        countRightAnswers = 0;
        totalCount = 0;
        idRightAnswer = 0;
        studentVariant = null;
        test = null;
        taskList = null;
    }

    public String getFunctionForGroup(){

//        Получаем группу, для кот. выполняется задание
        Course_variation course_variation = (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("studentCourseVar");
        Group group = groupDAO.getGroupByCourseVar(course_variation);

        String function;

//        Проверяем и получаем функцию оценивания для группы
        EstimationFunction estFunction = null;
        List<EstimationFunc_Group> func_groupList = func_groupDAO.getFunc_Group();
        if (func_groupList.size() > 0){
            for (EstimationFunc_Group func_group: func_groupList){
                if (func_group.getGroup().equals(group)){
                    estFunction = func_group.getFunction();
                    break;
                }
            }
        }

//            Если у группы есть функция оценивания
        if (estFunction != null)
            function = estFunction.getFunction();
//                Если нет, то ставим в соответствие следующую функцию
        else {
            function = "5*x*x+1.5"; // По скольку студент в любом случае проходит этот курс сейчас. Иначе
//            он не имел бы доступ к данному тесту
        }
        return function;
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

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
