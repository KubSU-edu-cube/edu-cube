package edu.kubsu.fpm.TestingModule;

import com.sun.jmx.snmp.tasks.Task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Марина
 * Date: 29.04.11
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */

// TO DO:
//        1.    Проверить, что собственно записано в факт в БД: структура DOM или просто текст -> вспомнить инструменты для работы с потоками и DOM-документами
//


@ManagedBean(name = "taskBean")
@SessionScoped
public class TaskBean {

    private String testResult = "";     //  Возвращает строчку с результатом тестирования
    private Integer countRightAnswer = 0;    //  Содержит колличество парвильных ответов студента
    private Integer countAnswer = 0;    //  Содержит колличество заданных вопросов
    private String studentAnswer = "";  //  Содержит текущий ответ студента
    private String rightAnswer;         //  Содержит текущий правильный ответ на заданный вопрос
    private String currentQuestion;     //  Содержит текст текущего вопроса
    private List<Integer> idFactList;   //  Лист id-ов фактов, кот. входят в прочитанную лекцию
    private List<Boolean> wasAsked;     //  Лист, показывающий, спрашивали мы по этому факту уже или нет
    private List<Integer> idObligitaryFactList;    //   Список id-ов обязательных фактов
    private Connection conn;            //  Устанавливает соединение с базой
    private int countQuestion;      //  Колличество вопросов, кот. еще нужно задать
    private boolean isOblig = true;   //  Сейчас будут задаваться обязательные вопросы или нет

//    Конструктор класса
    public TaskBean(){
        idFactList = new ArrayList<Integer>();  //  По-идее их нужно получать из класса генерции лекции
        idObligitaryFactList = new ArrayList<Integer>();
        wasAsked = new ArrayList<Boolean>();
        idFactList.add(2);  //  Но мы пока что будем извращаться
        idFactList.add(5);
        idFactList.add(1);
        idFactList.add(6);
        idFactList.add(3);
        idFactList.add(4);
//        Теперь вообще хотелось бы знать число обязательных фактов в лекции
//        Поскольку персистентность еще не работает, то делаем так
        try{
            Connection connection = getConn();
            String currentQuestion = "";
            for (Integer id : idFactList){
                PreparedStatement statement = connection.prepareStatement("SELECT OBLIGATORY FROM APP.FACT WHERE ID = " + id);
                ResultSet resultSet = statement.executeQuery();
                if ((resultSet.next())&&(resultSet.getInt("OBLIGATORY") == 1))
                    idObligitaryFactList.add(id);   //  Добавляем id обязательного факта
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Число обязательных фактов, на основе которых будут заданы вопросы
        countAnswer = idObligitaryFactList.size()/5;
        if (countAnswer == 0)
            countAnswer = 1;
        countQuestion = countAnswer;

//        Инициализируем список посещенных фактов
        for (int i=0; i < idFactList.size(); i++)
            wasAsked.add(false);
    }

//    Проверяет текущий ответ студента
    public String checkAnswer(){
        String url = "student_test";
//        if (rightAnswer.equals(studentAnswer)){
//            countRightAnswer++;
//        }
//        Если заданы все вопросы
//        if ((countQuestion == 1)&&(isOblig == false))
            url = "student_mark";
//         Если у нас закончились обязательные вопросы
        if ((countQuestion == 0)&&(isOblig == false)){
            int k = 0;      //  Процент доп. вопросов
    //        Когда закончились обязательные вопросы, должен достать из базы чисо доп. вопросов k
//            Получаем число доп. вопросов
            countQuestion = (idFactList.size() - idObligitaryFactList.size())*k /100;
            if (countQuestion == 0)
                countQuestion = 1;
            countAnswer += countQuestion;
        }
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
        currentQuestion = "";
//        Если задаем обязательный вопрос
        if (isOblig){
//            Выбираем произвольно id факта, на основе кот. сейчас будем задавать вопрос
            int id = getId(idObligitaryFactList.size());
            currentQuestion = generateQuestion(id);
            countQuestion --;
            if (countQuestion == 0){
                isOblig = false;
            }
        }
        else{
//            Если обязательные вопросы кончились
            int id = getId(idFactList.size());
            currentQuestion = generateQuestion(id);
//            Для того, чтобы не зациклится и не получить число доп. вопросов во второй раз
            if (countQuestion != 1)
                countQuestion--;
        }
        return currentQuestion;
    }

//    Возвращает id факта из диапазона от 0..n, на основе которого еще не задавался вопрос
    private int getId(int n){
        Random r = new Random();
        int id = 0;
        boolean t = true;
        while (t){
            id = r.nextInt(n);
            if (wasAsked.get(id) == false){
                wasAsked.set(id, true);
                t = false;
            }
        }
        return id;
    }

//    Генерирует текст вопроса на основе факта с заданным id
//    Должен сохранять правильный ответ на сгенерированный вопрос
    private String generateQuestion(int id){
        String quest = "";
        Connection conn = getConn();
            try{
                PreparedStatement statement = conn.prepareStatement("SELECT CONTENT FROM APP.FACT WHERE ID =" + id);
//                Получаем факт
            } catch (SQLException ex) {
            Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);
            }
//        Дописать алгоритм
        return quest;
    }

    public Connection getConn() {

        try{
             Class.forName("org.apache.derby.jdbc.ClientDriver");
             conn = DriverManager.getConnection("jdbc:derby://localhost:1527/FactsStore", "admin", "admin");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return conn;
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

}
