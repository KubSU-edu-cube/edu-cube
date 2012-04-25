package edu.kubsu.fpm.managed.adaptiveTesting;

import edu.kubsu.fpm.DAO.FactDAO;
import edu.kubsu.fpm.DAO.SynAntDAO;
import edu.kubsu.fpm.DAO.WordsDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Марина
 * Date: 29.04.11
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */

// TODO:
//    1!. Преобразовать все запросы к Persistence модели.
//        1.    Учесть, что в тексте факта слова могут начинаться как с маленьких, так и с больших букв.
//        3.    Дописать алгоритм генерации тестов
//        3.5.  Предусмотреть, чтобы тестовые задания не повторялись.
//        4.    Отладить все, что уже сделала.           - хорошо звучит ;-))
//        5.    Реализовать достование количества доп. вопросов из базы в зависимости от процента правильных ответов
//


@ManagedBean(name = "taskBean")
@SessionScoped
public class TaskBean {

    private String testResult = "";     //  Возвращает строчку с результатом тестирования
    private Double countRightAnswer = 0.0;    //  Содержит колличество парвильных ответов студента
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
    private int typeQuestion = 0;       //  Тип генерируемого вопроса
    private int idGroup;                //  id текущей группы
    private int idCourse;               //  id текущего курса
    private EntityManager entityManager;

    @EJB
    private FactDAO factDAO;

    @EJB
    private WordsDAO wordsDAO;
    
    @EJB
    private SynAntDAO synAntDAO;

//    Конструктор класса
    public TaskBean(){
        idFactList = new ArrayList<>();  //  По-идее их нужно получать из класса генерции лекции
        idObligitaryFactList = new ArrayList<>();
        wasAsked = new ArrayList<>();
        idFactList.add(2);  //  Но мы пока что будем извращаться
        idFactList.add(5);
        idFactList.add(1);
        idFactList.add(6);
        idFactList.add(3);
        idFactList.add(4);
        idCourse = 1;
        idGroup = 1;
        List<Integer> obligitaryFactList;
//        Теперь вообще хотелось бы знать число обязательных фактов в лекции
        for (Integer id : idFactList){
            obligitaryFactList = factDAO.getObligitaryFactById(id);
            for(Integer obligatory: obligitaryFactList){
                if (obligatory == 1)
                    idObligitaryFactList.add(id);   //  Добавляем id обязательного факта
            }
        }

//        Число обязательных фактов, на основе которых будут заданы вопросы
        countAnswer = idObligitaryFactList.size()/5; // TODO учесть, что процент обязат. вопросов настравиается.
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
//        else if(typeQuestion == 2){
////                Получаем список синонимов
//            int synAntId = synAntDAO.getSynAntById();
//            Words words = new Words();
//            List<SynAnt> synAntList = words.getSynAntList();
//            for (SynAnt synAnt: synAntList){
//
//            }
//            // select s.relation from Words w, SynAnt s Where w.id = s.id and w.word = :word and s.relation = :relation
//            List<Integer> idDependList = wordsDAO.getIdDependByWordAndRelation(rightAnswer, "SYN");
//            List<String> synonym = new ArrayList<>();
//            for (Integer idDepend: idDependList){
//                    Words words = wordsDAO.getWordsById(idDepend);
//                    synonym.add(words.getWord());
//            }
//            for (String aSynonym : synonym)
//                if (aSynonym.equals(studentAnswer))
//                    countRightAnswer += 0.5;
//        }
////        Если заданы все вопросы
//        if ((countQuestion == 1)&&(!isOblig))
//            url = "student_mark";
////         Если у нас закончились обязательные вопросы
//        if ((countQuestion == 0)&&(!isOblig)){
//          double percent = countRightAnswer*100/countAnswer;      // TODO Учесть, что процент доп. вопросов настраивается.
//    //        Когда закончились обязательные вопросы, достаем из базы чисо доп. вопросов
////            Получаем число доп. вопросов
////            countQuestion = getAmountAddQuest(percent);
//            countAnswer += countQuestion;
//        }
        return url;
    }

    //      Возвращает число доплнительных вопросов из базы
//    private int getAmountAddQuest(double percent) {   // TODO Должно быть такое понятие, как значение процентов по-умолчанию.
//        int k = 0;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("select percent_rightansers from app.aditional_question where classif_valuesid = " + idCourse);
//            ResultSet resultSet = preparedStatement.executeQuery();
////              Находим самое ближайшее к текущему значение процентов в базе
//            double min = percent;
//            double p = percent;
//            while (resultSet.next()){
//                if (abs(resultSet.getDouble("percent_rightansers") - percent) < min){
//                    min = abs(resultSet.getDouble("percent_rightansers") - percent);
//                    p = resultSet.getDouble("percent_rightansers");
//                }
//            }
//            preparedStatement = connection.prepareStatement("select quest_amount from app.aditional_question where classif_valuesid = " + idCourse +
//                    " and percent_rightansers = " + p);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next())
//                k = resultSet.getInt("quest_amount");
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        int amountRemainQuestion = idFactList.size() - countAnswer;
//        if (k > amountRemainQuestion)
//            k = amountRemainQuestion;
//        return k;
//    }

      private double abs(double z){
        if (z < 0.0)
            z = - z;
        return z;
    }

//    Формирует вывод результатов тестирования
    public String getTestResult() {
        testResult = "Вы получили ".concat(this.getCountRightAnswer().toString()).concat(" балов из ").concat(this.getCountAnswer().toString());
        return testResult;
    }

//   Генерирует текущий тестовый вопрос
    public String getCurrentQuestion() {
//        Почему-то не переводится каретка
        currentQuestion = "abc";
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
            if (!wasAsked.get(id)){
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
//        Connection conn = getConn();
//            try{
//                PreparedStatement statement = conn.prepareStatement("SELECT CONTENT, CONTENT_TYPE FROM APP.FACT WHERE ID =" + id);
//                ResultSet resultSet = statement.executeQuery();
////                Получаем содержимое факта
//                if (resultSet.next()){
//                    String content = resultSet.getString("CONTENT_TYPE");
////                    Если это текст
//                    switch (content) {
//                        case "text":
//                            String factText = getContentFact(resultSet);
////                        Сгенерировать из полученного текста вопрос и запомнить правильный ответ
////                        В произвольном порядке выбираем тип вопроса
//                            Random r = new Random();
//                            typeQuestion = r.nextInt(2) + 1;
//                            switch (typeQuestion) {
//                                case 1: {    //  Генерируем вопрос с вариантом выбора
//                                    String[] words = factText.split(" ");  //  Получаем массив слов, состовляющих текст факта, чтобы выбрать одно из них случайно
//                                    ResultSet rs;
//                                    PreparedStatement ps;
//                                    int countAnt = 0;       //  Число антонимов
//                                    boolean f = true;
//                                    int i = 0;      //  Номер выбранного слова
//                                    do {
//                                        i = r.nextInt(words.length);
//                                        //                                Получаем все антонимы от выбранного слова
//                                        ps = conn.prepareStatement("select s.IDDEPEND from app.WORDS w right outer join app.syn_ant s on w.id = s.id where w.WORD = '" + words[i] + "' and s.RELATION='ANT'");
//                                        rs = ps.executeQuery();        //      Получили id-шники антонимов данного слова
//                                        if (rs.next()) {
//                                            f = false;      //  Значит мы все нашли: слово и его антонимы
//                                        }
//                                    } while (f);
////                                Получаем список антонимов
//                                    List<String> antonym = new ArrayList<>();
//                                    do {
//                                        ps = conn.prepareStatement("SELECT WORD FROM APP.WORDS WHERE ID =" + rs.getInt(1));
//                                        ResultSet resSet = ps.executeQuery();
//                                        if (resSet.next())
//                                            antonym.add(resSet.getString("WORD"));
//                                        countAnt++;
//                                    } while (rs.next());
//                                    Integer numberRightAnswer;      //  Номер по порядку, кот. будет выводится правильный ответ
//                                    if (countAnt > 4)
//                                        countAnt = 4;   //  По-хорошему их надо выбирать рэндомом, чтобы генерировать вопросы на основе различных антонимов
//                                    numberRightAnswer = r.nextInt(countAnt) + 1;
//                                    rightAnswer = numberRightAnswer.toString();
//                                    quest = "Выберете правильный вариант из предложенных. В качестве ответа введите номер этого варианта \n";
//                                    String[] wrongFact;
//                                    for (Integer j = 1; j < numberRightAnswer; j++) {
//                                        wrongFact = words;
//                                        wrongFact[i] = antonym.get(j - 1);
//                                        quest += j.toString() + ". " + getStringFromArray(wrongFact) + "; \n";
//                                    }
//                                    quest += numberRightAnswer.toString() + ". " + getStringFromArray(words) + "; \n";
//                                    for (Integer j = numberRightAnswer + 1; j <= countAnt + 1; j++) {
//                                        wrongFact = words;
//                                        wrongFact[i] = antonym.get(j - 2);
//                                        quest += j.toString() + ". " + getStringFromArray(wrongFact) + "; \n";
//                                    }
//                                    break;
//                                }
//                                case 2: {     //  Генерируем вопрос с пропущенным словом
//                                    String[] words = factText.split(" ");  //  Получаем массив слов, состовляющих текст факта, чтобы выбрать одно из них случайно
//                                    int i;      //  Номер выбранного слова
//                                    i = r.nextInt(words.length);
//                                    rightAnswer = words[i];
//                                    words[i] = " ... ";
//                                    quest = getStringFromArray(words);
//                                    break;
//                                }
//                                case 3: {     //  Генерируем вопрос с развернутым ответом
//                                    quest = "Вопрос с развернутым ответом";
//                                    rightAnswer = "";
//                                    break;
//                                }
//                            }
//                            break;
////                    Если это изображение
//                        case "image":
//                            quest = "Это вопрос на основе изображения";
//                            rightAnswer = "";
//                            break;
////                    Если это формула
//                        case "formula":
//                            quest = "Это вопрос на основе формулы";
//                            rightAnswer = "";
//                            break;
//                    }
//                }
//            } catch (SQLException ex) {
//            Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
        return quest;
    }

    //     Преобразует массив строк в одну строку
    private String getStringFromArray(String [] sArray){
        String s = "";
        for (String aSArray : sArray) s += aSArray + " ";
        return s;
    }

//    Получаем содержимое факта, если оно - текст
    private String getContentFact(ResultSet resultSet){
        Blob text;
        InputStream is = null;
        try {
            text = resultSet.getBlob("CONTENT");
            is = new ByteArrayInputStream(text.getBytes(1, (int)text.length()));
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//                    Получаем структуру DOM факта
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document fact = null;
        try {
                            builder = builderFactory.newDocumentBuilder();
                            fact = builder.parse(is);
                        } catch (ParserConfigurationException | SAXException | IOException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
        //                    Получаем корневой элемент
                        Element root;
                        root = fact.getDocumentElement();
        //                    Идем по всем узлам
        String txt = "";
                        for (Node child = root.getFirstChild(); child != null; child = root.getNextSibling()){
                            txt += child.getFirstChild().getNodeValue();  //  Получаем листы - текст факта
                        }
        return txt;
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

    public Double getCountRightAnswer() {
        return countRightAnswer;
    }

    public void setCountRightAnswer(Double countRightAnswer) {
        this.countRightAnswer = countRightAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

}