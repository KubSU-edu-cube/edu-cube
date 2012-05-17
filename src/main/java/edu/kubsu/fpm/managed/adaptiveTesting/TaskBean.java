package edu.kubsu.fpm.managed.adaptiveTesting;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.ejb.DBImageLocal;
import edu.kubsu.fpm.model.AdditionalQuestion;
import edu.kubsu.fpm.model.FactCollection;
import edu.kubsu.fpm.model.SynAnt;
import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.Parameter;
import net.sourceforge.jeuclid.converter.Converter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

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
    private Double countRightAnswer = 0.0;    //  Содержит колличество парвильных ответов студента
    private Integer countAnswer;    //  Содержит колличество заданных вопросов
    private String studentAnswer = "";  //  Содержит текущий ответ студента
    private String rightAnswer;         //  Содержит текущий правильный ответ на заданный вопрос
    private String currentQuestion = "abc";     //  Содержит текст текущего вопроса
    private List<Integer> idFactList;   //  Лист id-ов фактов, кот. входят в прочитанную лекцию
    private List<Boolean> wasAsked;     //  Лист, показывающий, спрашивали мы по этому факту уже или нет
    private List<Integer> idObligitaryFactList;    //   Список id-ов обязательных фактов
    private Integer countQuestion;      //  Колличество вопросов, кот. еще нужно задать
    private boolean isOblig = true;   //  Сейчас будут задаваться обязательные вопросы или нет
    private int typeQuestion = 0;       //  Тип генерируемого вопроса
    private int idGroup;                //  id текущей группы
    private List<Integer> obligFactList;
    private List<String> participleList;    // Слова-частцы, а так же предлоги, союзы и т.п. на основе кот. не нужно строить вопросы.
    private String targetURL;
    private Integer studentId;

    @EJB
    private FactDAO factDAO;

    @EJB
    private WordsDAO wordsDAO;
    
    @EJB
    private SynAntDAO synAntDAO;

    @EJB
    private AdditionalQuestionDAO additionalQuestionDAO;

    @EJB
    private GroupDAO groupDAO;

    @EJB
    private DBImageLocal dbImage; // сюда будут переданы все картинки

    //    Конструктор класса
    public TaskBean(){
        idFactList = new ArrayList<>();  //  По-идее их нужно получать из класса генерции лекции
        wasAsked = new ArrayList<>();
        participleList = new ArrayList<>();
        idFactList.add(2);  //  Но мы пока что будем извращаться
        idFactList.add(5);
        idFactList.add(7);
        idFactList.add(8);
        idFactList.add(9);
        idFactList.add(10);
        idGroup = 1;
        isOblig = true;

//        Инициализируем список посещенных фактов
        for (Integer anIdFactList : idFactList) wasAsked.add(false);

        participleList.add("-");
        participleList.add("в");
        participleList.add("на");
        participleList.add("за");
        participleList.add("по");
        participleList.add("с");
        participleList.add("не");
        participleList.add("у");
        participleList.add("для");
        participleList.add("ни");
        participleList.add("что");
        participleList.add("из");
        participleList.add("при");
        participleList.add("n");
        participleList.add("это");
        
        String student = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId");
        studentId = Integer.parseInt(student == null ? "0" : student);
    }

    //   Генерирует текущий тестовый вопрос
    public String getCurrentQuestion() {
//        Получаем число обязательных фактов в лекции
        idObligitaryFactList = getObligitaryFactList();
//        Число обязательных фактов, на основе которых будут заданы вопросы
        countQuestion = getCountQuestion();
        countAnswer = getCountAnswer();

//        Если задаем обязательный вопрос
        if (isOblig){
//            Выбираем произвольно id факта, на основе кот. сейчас будем задавать вопрос
            int id = getId(idObligitaryFactList);
            currentQuestion = generateQuestion(id);
            countQuestion --;
            if (countQuestion == 0){
                isOblig = false;
            }
        }
        else{
            int id = getId(idFactList);
            currentQuestion = generateQuestion(id);
//            Для того, чтобы не зациклится и не получить число доп. вопросов во второй раз
            if (countQuestion != 1)
                countQuestion--;
        }
        return currentQuestion;
    }

    public Integer getCountQuestion() {
        if (countQuestion == null){
            int obligPersent;
            if (idGroup > 0){  // Если пользователь - зарегестрирован и отноится к некоторой группе
                obligPersent = additionalQuestionDAO.getPercentObligatoryQuestion(groupDAO.getGroupsById(idGroup));

                if (obligPersent == 0){       // Если в базе ничего не нашли или задан 0, исп. значение по-умолчанию.
                    obligPersent = 20;
                }
            }
            else
                obligPersent = 20;
    //        Учитываем, что процент обязательных вопросов задается из базы.
            int count = idObligitaryFactList.size() * obligPersent / 100;
            return count == 0 ? 1 : count;
        }
        else
            return countQuestion;
    }
    
    public List<Integer> getObligitaryFactList() {
        if (obligFactList == null){
            obligFactList = new ArrayList<>();
            for (Integer id : idFactList){
                if (factDAO.getObligitaryFactById(id) == 1)
                    obligFactList.add(id);   //  Добавляем id обязательного факта
            }
            return obligFactList;
        }
        else
            return obligFactList;
    }

    //    Проверяет текущий ответ студента
    public void checkAnswer(){
        targetURL = "student_test";
        if (rightAnswer.toLowerCase().equals(studentAnswer.toLowerCase())){
            countRightAnswer++;
        }
        else if(typeQuestion == 2){
//                Получаем список синонимов
            List<String> synList = getSynAntList(rightAnswer, "SYN");
            for (String syn : synList)
                if (syn.equals(studentAnswer))
                    countRightAnswer += 0.5;
        }
//        Если заданы все вопросы
        if ((countQuestion == 1)&&(!isOblig))
            targetURL = "student_mark";
//         Если у нас закончились обязательные вопросы, то получаем число доп. вопросов
        if ((countQuestion == 0)&&(!isOblig)){
//            Процент вопросов, на кот. студен ответил верно.
            int percentRightAnswers = (int) (countRightAnswer * 100 / countAnswer);

            countQuestion = getAmountAddQuest(percentRightAnswers);
            countAnswer += countQuestion;
            if (countQuestion == 0)
                targetURL = "student_mark";
            else
                countQuestion++;
        }
        studentAnswer = "";
    }

//      Возвращает число доплнительных вопросов из базы
    private int getAmountAddQuest(int percentRightAnswers) {
        int amountRemainQuestion = idFactList.size() - countAnswer;
        int count = amountRemainQuestion * 50 / 100;  // значение по-умолчанию.

        if (idGroup > 0){
            List<AdditionalQuestion> addQuestList = additionalQuestionDAO.getAddQuestByGroup(groupDAO.getGroupsById(idGroup));
            if (addQuestList.size() > 0){
                int percent = getClosePercent(percentRightAnswers, addQuestList);
                for (AdditionalQuestion addQuest: addQuestList){
                    if (addQuest.getPercentRigthAnswers() == percent) {
                        int percentAddQuest = addQuest.getPercentAdditionalQuestion();
                        count = amountRemainQuestion * percentAddQuest / 100;
                    }
                }
            }
        }
        return count;
    }

    private int getClosePercent(int percentRightAnswers, List<AdditionalQuestion> addQuestList) {
        int result = 0;
        int min = percentRightAnswers;
        for (AdditionalQuestion addQuest: addQuestList){
            if (abs(addQuest.getPercentRigthAnswers() - percentRightAnswers) < min){
                min = abs(addQuest.getPercentRigthAnswers() - percentRightAnswers);
                result = addQuest.getPercentRigthAnswers();
            }
        }
        return result;
    }

    private int abs(int z){
        if (z < 0)
            z = - z;
        return z;
    }

//    Формирует вывод результатов тестирования
    public String getTestResult() {    // Подумать над формированием результата. На что смотреть при выборе функции оценивания?
        // TODO Округлять оценку до целого! Вычислять выражение от процента.
        FunctionToGroupBean functionToGroupBean = new FunctionToGroupBean();
        String function = functionToGroupBean.getFunctionForGroup(idGroup, studentId);
//        Expression
        testResult = "Вы получили ".concat(this.getCountRightAnswer().toString()).concat(" балов из ").concat(this.getCountAnswer().toString());
        return testResult;
    }

//    Возвращает id факта из диапазона от 0..n, на основе которого еще не задавался вопрос
    private int getId(List<Integer> list){
        Random r = new Random();
        int id = 0;
        boolean t = true;
        while (t){
            id = r.nextInt(list.size());
            FactCollection factCollection = factDAO.getCollectionByFactId(list.get(id));
            if ((!wasAsked.get(id))&&(!factCollection.getFactcollName().equals("доказательство"))){
                wasAsked.set(id, true);
                t = false;
            }
        }
        return list.get(id);
    }

    public String addImgContent(int curImg) {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        return "<img src=\"http://localhost:8080" + contextPath +"/DBImageServlet?imgcount="+ curImg
                + "\" alt=\"Вопрос-картика\" height=\"200\" width=\"200\"/>";
    }

    public String addImgContent(int curImg, int height, int width) {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        return "<img src=\"http://localhost:8080" + contextPath +"/DBImageServlet?imgcount="+ curImg
                + "\" alt=\"Вопрос-картика\" height=\"" + height +" \" width=\"" + width + "\"/>";
    }

    private String getStringFromByteText(byte[] bytes) {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(bytes));
        BufferedReader br = new BufferedReader(isr);
        StringBuilder buf = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return buf.toString();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

//    Генерирует текст вопроса на основе факта с заданным id
//    Должен сохранять правильный ответ на сгенерированный вопрос
    private String generateQuestion(int id){
        int curImg = 0;
        String quest = "";
        String contentFact = factDAO.getContentTypeFactById(id);
        List<byte[]> byteImgList = new ArrayList<>();
        Element root = getRootElementByFactId(id);
        NodeList children = root.getChildNodes();
        switch (contentFact){
            case "text":
                Map<Integer, String> factMap = new HashMap<>();
                List<String> factList = new ArrayList<>();
                for (int i = 0; i < children.getLength(); i++){
                    Node currentNode = children.item(i);
                    if (currentNode.getNodeType() == Node.ELEMENT_NODE){
                        Element currentElement = (Element) currentNode;
                        String content = currentElement.getTextContent();

                        // Если тип - текст, то генерирум вопрос только на основе текста.
                        if (currentElement.getTagName().equals("fact_text")) {
                            String text = getStringFromByteText(content.getBytes());    // Вот эта строка уже содержимое - текст!!!
                            factList.add(text);
                            factMap.put(factList.size() - 1, text);
                        }
                        else if (currentElement.getTagName().equals("fact_image")) {
                            byteImgList.add(curImg, Base64.decode(content));    //складываем картинки в лист
                            factList.add(addImgContent(curImg));
                            curImg += 1;
                        }
                        else if (currentElement.getTagName().equals("math")) {
                            LayoutContextImpl layoutContext = (LayoutContextImpl) LayoutContextImpl.getDefaultLayoutContext();
                            layoutContext.setParameter(Parameter.MATHSIZE, 20);

                            BufferedImage formulaImage = null;
                            try {
                                formulaImage = Converter.getInstance().render(currentElement, layoutContext);
                            } catch (IOException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }

                            byteImgList.add(curImg, buffImgToBytes(formulaImage));
                            factList.add(addImgContent(curImg, 30, 30));
                            curImg += 1;
                        }
                    }
                }

                // Генерируем текстовый вопрос. Правильный ответ будет запомнен в функции.
                for (Integer key: factMap.keySet()){
                    quest = genTextQuest(factMap.get(key), key, factList);
                    if (!quest.equals(""))
                        break;
                }
                break;
            case "image":
                typeQuestion = 2;
                for (int i = 0; i < children.getLength(); i++){
                    Node currentNode = children.item(i);
                    if (currentNode.getNodeType() == Node.ELEMENT_NODE){
                        Element currentElement = (Element) currentNode;
                        String content = currentElement.getTextContent();

                        // Если факт содержит картинку, то на ее основе и созадем вопрос.
                        // TODO Добавить сюда отображение SVG - jeuclid.Для этого нужно знать тег для svg.
                        if (currentElement.getTagName().equals("fact_image")) {
                            byteImgList.add(curImg, Base64.decode(content));    //складываем картинки в лист
                            quest = "Что изображено на рисунке?<br /><br />" + addImgContent(curImg);  //наращиваем строку вопроса
                            curImg += 1;
                            FactCollection factCollection = factDAO.getCollectionByFactId(id);
                            rightAnswer = factCollection.getFactcollName();
                            break;
                        }
                    }
                }
                break;
            case "formula":
                typeQuestion = 2;
                for (int i = 0; i < children.getLength(); i++){
                    Node currentNode = children.item(i);
                    if (currentNode.getNodeType() == Node.ELEMENT_NODE){
                        Element currentElement = (Element) currentNode;
                        String content = currentElement.getTextContent();

                        if (currentElement.getTagName().equals("math")) {
                            LayoutContextImpl layoutContext = (LayoutContextImpl) LayoutContextImpl.getDefaultLayoutContext();
                            layoutContext.setParameter(Parameter.MATHSIZE, 20);

                            BufferedImage formulaImage = null;
                            try {
                                formulaImage = Converter.getInstance().render(currentElement, layoutContext);
                            } catch (IOException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }

                            byteImgList.add(curImg, buffImgToBytes(formulaImage));
                            quest = "Что определяет данная формула?<br /><br />" + addImgContent(curImg, 30, 30);
                            curImg += 1;
                            FactCollection factCollection = factDAO.getCollectionByFactId(id);
                            rightAnswer = factCollection.getFactcollName();
                            break;
                        }
                    }
                }
                break;
        }
        dbImage.setImgList(byteImgList);
        curImg = 0;
        return quest;
    }

    private String genTextQuest(String questTetx, Integer idFactList, List<String> factList) {
//          В произвольном порядке выбираем тип вопроса
        Random r = new Random();
        typeQuestion = r.nextInt(2) + 1;
        String quest = null;
        switch (typeQuestion) {
            case 1:     //  Генерируем вопрос с вариантом выбора

//                  Получаем массив слов, состовляющих текст факта, чтобы выбрать одно из них случайно
                String[] words = getWordsArrayByString(questTetx);
                List<Integer> wasFind = new ArrayList<>();
                boolean f = true;
                Integer i = 0;      //  Номер выбранного слова
                List<String> antList = new ArrayList<>();
                do {
                    i = r.nextInt(words.length);
                    if ((!wasFind.contains(i)) && (!participleList.contains(words[i]))) {
                        //                      Получаем все антонимы от выбранного слова
                        antList = getSynAntList(words[i], "ANT");
                        if (!antList.isEmpty()) {
                            f = false;
                        }
                        wasFind.add(i);
                    }
                } while (f);
                Integer numberRightAnswer;      //  Номер по порядку, кот. будет выводится правильный ответ
                int countAnt = antList.size() > 4 ? 4 : antList.size();
                numberRightAnswer = r.nextInt(countAnt) + 1;
                rightAnswer = numberRightAnswer.toString();
                quest = "Выберете правильный вариант из предложенных. В качестве ответа введите номер этого варианта <br /><br />";
                String wrongFact;
                for (Integer j = 1; j < numberRightAnswer; j++) {
                    wrongFact = questTetx.replace(words[i], antList.get(j-1));
                    quest += j.toString() + ". ";
                    for (String fact: factList){
                        if (factList.get(idFactList).equals(fact))
                            quest += wrongFact;
                        else
                            quest += fact;
                    }
                    quest += "<br />";
                }
                quest += numberRightAnswer.toString() + ". " + listToString(factList) + "<br />";
                for (Integer j = numberRightAnswer + 1; j <= countAnt + 1; j++) {
                    wrongFact = questTetx.replace(words[i], antList.get(j-2));
                    quest += j.toString() + ". ";
                    for (String fact: factList){
                        if (factList.get(idFactList).equals(fact))
                            quest += wrongFact;
                        else
                            quest += fact;
                    }
                    quest += "<br />";
                }
                break;
            case 2:      //  Генерируем вопрос с пропущенным словом
//                  Получаем массив слов, состовляющих текст факта, чтобы выбрать одно из них случайно
                String[] word = getWordsArrayByString(questTetx);
                f = true;
                Integer j;
                do{     // пропускаем предлоги
                    j = r.nextInt(word.length);
                    if (!participleList.contains(word[j]))
                        f = false;
                } while (f);
                rightAnswer = word[j];
                quest = "Введите пропущенное слово.<br/ ><br />";
                String editFact = questTetx.toLowerCase().replaceFirst(word[j], "...");
                for (String fact: factList){
                    if (factList.get(idFactList).equals(fact))
                        quest += editFact;
                    else
                        quest += fact;
                }
                quest += "<br />";
                break;
        }
        return quest;
    }

    private List<String> getSynAntList(String word, String relation) {
        List<String> antList = new ArrayList<>();
        List<SynAnt> synAntList = wordsDAO.getListSynAntByWord(word);
        List<SynAnt> synAntListDep = wordsDAO.getListDependSynAntByWord(word);

        for (SynAnt synAnt : synAntList) {
            if (synAnt.getRelation().equals(relation)) {
                antList.add(wordsDAO.getWordsById(synAnt.getSynAntPK().getIddepend()).getWord());
            }
        }
        for (SynAnt synAnt : synAntListDep) {
            if (synAnt.getRelation().equals(relation)) {
                antList.add(wordsDAO.getWordsById(synAnt.getSynAntPK().getId()).getWord());
            }
        }
        return antList;
    }

    private String[] getWordsArrayByString(String text) {
        String tempText = text.replace("\n\t", "")
                .replace("\t\n", "")
                .replace("\n", "")
                .replace("\t", "")
                .replace(",", "")
                .replace(".", "")
                .replace(":", "")
                .toLowerCase();
        return tempText.split(" ");
    }

    private String listToString(List<String> factList) {
        String text = "";
        for (String fact: factList)
            text += fact + " ";
        return text;
    }

    private byte[] buffImgToBytes(BufferedImage bufferedImage) {
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Element getRootElementByFactId(int id) {
        Element root = null;
        Serializable factContent = factDAO.getContentFactById(id);
        byte[] bContent = (byte[]) factContent;
        InputStream is = new ByteArrayInputStream(bContent);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = null;
            try {
                doc = builder.parse(is);
            } catch (SAXException | IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            root = doc.getDocumentElement();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return root;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public Integer getCountAnswer() {
        if ((countAnswer == null)&&(countQuestion != null)){
            return countQuestion;
        }
        else
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

    public String getURL() {
        studentAnswer = "";
        return targetURL;
    }

}