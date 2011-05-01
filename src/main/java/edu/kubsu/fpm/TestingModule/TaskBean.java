package edu.kubsu.fpm.TestingModule;

import com.sun.jmx.snmp.tasks.Task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by IntelliJ IDEA.
 * User: Марина
 * Date: 29.04.11
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "taskBean")
@SessionScoped
public class TaskBean {

    private String testResult = "";     //  Возвращает строчку с результатом тестирования
    private Integer countRightAnswer = 0;    //  Содержит колличество парвильных ответов студента
    private Integer countAnswer = 0;    //  Содержит колличество заданных вопросов
    private String studentAnswer = "";  //  Содержит текущий ответ студента
    private String rightAnswer;         //  Содержит текущий правильный ответ на заданный вопрос
    private Integer currentCountQuestion;   //  Число вопросов, заданных на данный момент
    private String currentQuestion;     //  Содержит текст текущего вопроса

//    Проверяет текущий ответ студента
    public String checkAnswer(){
        String url = "student_test";
//        if (rightAnswer.equals(studentAnswer)){
//            countRightAnswer++;
//        }
//        if (currentCountQuestion.equals(countAnswer))
            url = "student_mark";
        return url;
    }

//    Формирует вывод результатов тестирования
    public String getTestResult() {
        testResult = "Вы ответили на ".concat(this.getCountRightAnswer().toString()).concat(" вопросов из ").concat(this.getCountAnswer().toString());
        return testResult;
    }

//   Генерирует текущий тестовый вопрос
    public String getCurrentQuestion() {
//        Почему-то не переводится каретка
        currentQuestion = "Здесь будет формироваться тестовый вопрос. \n А еще нужно придумать что-то с картинками.";
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public Integer getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(Integer countAnswer) {
        this.countAnswer = countAnswer;
    }

    public Integer getCurrentCountQuestion() {
        return currentCountQuestion;
    }

    public void setCurrentCountQuestion(Integer currentCountQuestion) {
        this.currentCountQuestion = currentCountQuestion;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Integer getCountRightAnswer() {
        return countRightAnswer;
    }

    public void setCountRightAnswer(Integer countRightAnswer) {
        this.countRightAnswer = countRightAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public TaskBean(){

    }
}
