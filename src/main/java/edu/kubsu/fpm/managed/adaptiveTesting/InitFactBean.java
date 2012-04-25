package edu.kubsu.fpm.managed.adaptiveTesting;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.model.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Marina
 * Date: 19.04.12
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@RequestScoped
public class InitFactBean {

    @EJB
    private ClassifierDAO classifierDAO;

    @EJB
    private ClassifierValueDAO classifierValueDAO;

    @EJB
    private FactCollectionDAO factCollectionDAO;

    @EJB
    private CollfactClassifvalueDAO collfactClassifvalueDAO;

    @EJB
    private FactDAO factDAO;

    @EJB
    private SynAntDAO synAntDAO;

    @EJB
    private WordsDAO wordsDAO;

    @EJB
    private AdditionalQuestionDAO additionalQuestionDAO;

    @EJB
    private GroupsDAO groupsDAO;

    public InitFactBean(){

    }

    public void tmpFactsInit(){
        persistClassifier();
        persistClassifierValue();
        persistCollection();
        persistCollfactClassifvalue();
        try{
            persistFact();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        persistSynAnt();
        persistAditionalQuestion();
    }                                                     

    private void persistAditionalQuestion() {     // TODO Проверить!
        persistAditionalQuestionValue(0, 22, 50, 100, 0);
        persistAditionalQuestionValue(0, 22, 50, 80, 10);
        persistAditionalQuestionValue(0, 22, 50, 60, 20);
        persistAditionalQuestionValue(0, 22, 50, 40, 30);
        persistAditionalQuestionValue(0, 22, 50, 20, 40);
    }

    private void persistAditionalQuestionValue(int groupId, int classifValuesId, int percentObligQuest, int percentRightAnsw, int percentAddQuest){
        AditionalQuestion aditionalQuestion = new AditionalQuestion();
        aditionalQuestion.setGroupid(groupsDAO.getGroupsById(groupId));
        aditionalQuestion.setClassifValuesid(classifierValueDAO.getClassifierValueById(classifValuesId));
        aditionalQuestion.setPercentObligatoryQuestion(percentObligQuest);
        aditionalQuestion.setPercentAdditionalQuestion(percentAddQuest);
        aditionalQuestion.setPercentRigthAnswers(percentRightAnsw);
    }

    private void persistSynAnt() {
        persistWordValue("совокупность");  // 1
        persistWordValue("множество");     // 2
        persistSynAntValue(1, 2, "SYN");
        persistWordValue("независимых");   // 3
        persistWordValue("зависимых");     // 4
        persistSynAntValue(3, 4, "ANT");
        persistWordValue("векторов");      // 5
        persistWordValue("скаляров");      // 6
        persistSynAntValue(5, 6, "ANT");
        persistWordValue("вектор");        // 7
        persistSynAntValue(5, 7, "SYN");
        persistWordValue("скаляр");        // 8
        persistSynAntValue(7, 8, "ANT");
        persistSynAntValue(6, 8, "SYN");
        persistWordValue("может");         // 9
        persistWordValue("не может");      // 10
        persistSynAntValue(9, 10, "ANT");
        persistWordValue("представим");    // 11
        persistWordValue("не представим"); // 12
        persistSynAntValue(11, 12, "ANT");
        persistWordValue("остальных");     // 13
        persistWordValue("других");        // 14
        persistSynAntValue(13, 14, "SYN");
        persistWordValue("последовательности"); // 15
        persistWordValue("ряда");               // 16
        persistSynAntValue(15, 16, "SYN");
        persistWordValue("последовательность"); // 17
        persistSynAntValue(15, 17, "SYN");
        persistWordValue("ряд");                // 18
        persistSynAntValue(16, 18, "SYN");
        persistSynAntValue(17, 18, "SYN");
        persistWordValue("ростом");             // 19
        persistWordValue("убыванием");          // 20
        persistSynAntValue(19, 20, "ANT");
        persistWordValue("точка");              // 21
        persistWordValue("линия");              // 22
        persistSynAntValue(21, 22, "ANT");
        persistWordValue("прямая");             // 23
        persistSynAntValue(22, 23, "SYN");
        persistWordValue("сходящейся");         // 24
        persistWordValue("сходящаяся");         // 25
        persistSynAntValue(24, 25, "SYN");
        persistWordValue("рассходящейся");      // 26
        persistWordValue("рассходящаяся");      // 27
        persistSynAntValue(26, 27, "SYN");
        persistSynAntValue(24, 26, "ANT");
        persistSynAntValue(25, 27, "ANT");
        persistWordValue("сходиться");          // 28
        persistWordValue("расходиться");        // 29
        persistSynAntValue(28, 29, "ANT");
        persistWordValue("числовой");           // 30
        persistWordValue("цифровой");           // 31
        persistSynAntValue(30, 31, "SYN");
        persistWordValue("символьной");         // 32
        persistSynAntValue(30, 32, "ANT");
        persistSynAntValue(31, 32, "ANT");
        persistWordValue("кривой");             // 33
        persistWordValue("кривая");             // 34
        persistSynAntValue(33, 34, "SYN");
        persistWordValue("прямой");             // 35
        persistSynAntValue(33, 35, "ANT");
        persistSynAntValue(34, 23, "ANT");
        persistSynAntValue(35, 23, "SYN");
        persistWordValue("любого");             // 36
        persistWordValue("некоторого");         // 37
        persistSynAntValue(36, 37, "ANT");
        persistWordValue("всякого");            // 38
        persistWordValue("каждого");            // 39
        persistSynAntValue(38, 39, "SYN");
        persistSynAntValue(36, 38, "SYN");
        persistSynAntValue(36, 39, "SYN");
        persistSynAntValue(38, 37, "ANT");
        persistSynAntValue(39, 37, "ANT");
        persistWordValue("существует");          // 40
        persistWordValue("не существует");       // 41
        persistSynAntValue(40, 41, "ANT");
        persistWordValue("увеличением");         // 42
        persistWordValue("уменьшением");         // 43
        persistSynAntValue(42, 43, "ANT");
        persistSynAntValue(42, 19, "SYN");
        persistSynAntValue(43, 20, "SYN");
        persistWordValue("убывают");             // 44
        persistWordValue("возрастают");          // 45
        persistSynAntValue(44, 45, "ANT");
        persistSynAntValue(45, 19, "SYN");
        persistSynAntValue(44, 20, "SYN");
        persistWordValue("монотонно");           // 46
        persistWordValue("не монотонно");        // 47
        persistSynAntValue(46, 47, "ANT");
        persistWordValue("неравенство");         // 48
        persistWordValue("равенство");           // 49
        persistSynAntValue(48, 49, "ANT");
        persistWordValue("один");                // 50
        persistWordValue("единственный");        // 51
        persistSynAntValue(50, 51, "SYN");
        persistWordValue("конечный");            // 52
        persistWordValue("бесконечный");         // 53
        persistSynAntValue(52, 53, "ANT");
    }

    private void persistWordValue(String value) {
        Words words = new Words();
        words.setWord(value);
        wordsDAO.persist(words);
    }

    private void persistSynAntValue(Integer id, Integer idDepend, String relation) {
        SynAnt synAnt = new SynAnt(id, idDepend);
        synAnt.setRelation(relation);
        synAntDAO.persist(synAnt);
    }

    private void persistFact() throws FileNotFoundException {
        persistFactValue(1, "text", "temp_facts/fact_1.xml", "легкая");
        persistFactValue(1, "text", "temp_facts/fact_2.xml", "средняя");
        persistFactValue(2, "text", "temp_facts/fact_3.xml", "легкая");
        persistFactValue(2, "text", "temp_facts/fact_4.xml", "средняя");
        persistFactValue(3, "text", "temp_facts/fact_5.xml", "легкая");
        persistFactValue(3, "text", "temp_facts/fact_6.xml", "средняя");
        persistFactValue(4, "text", "temp_facts/fact_7.xml", "легкая");
        persistFactValue(5, "text", "temp_facts/fact_8.xml", "легкая");
        persistFactValue(6, "text", "temp_facts/fact_8.xml", "легкая");
    }

    private void persistFactValue(Integer collId, String contentType, String fNname, String difficulty) {
        Fact fact = new Fact();
        fact.setCollection(factCollectionDAO.getCollectionById(collId));
        fact.setContentType(contentType);
        fact.setContent(getBytesFromTextFile(fNname));
        fact.setDifficultie(difficulty);
        factDAO.persist(fact);
    }

    private byte[] getBytesFromTextFile(String fName){
        FileInputStream fis = null;
        try {
            File textFile = new File(fName);
            byte[] bFile = new byte[(int) textFile.length()];
            fis = new FileInputStream(textFile.getAbsoluteFile());
            fis.read(bFile);
            return bFile;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void persistCollfactClassifvalue() {
        persistValueCollfactClassifvalue(1,1,22);
        persistValueCollfactClassifvalue(1,2,26);
        persistValueCollfactClassifvalue(1,1,25);
        persistValueCollfactClassifvalue(2,1,18);
        persistValueCollfactClassifvalue(2,2,26);
        persistValueCollfactClassifvalue(3,1,22);
        persistValueCollfactClassifvalue(3,2,26);
        persistValueCollfactClassifvalue(4,1,22);   // Пока что 22 вместо 19
        persistValueCollfactClassifvalue(4,2,28);
        persistValueCollfactClassifvalue(5,1,22);   // Пока что 22 вместо 19
        persistValueCollfactClassifvalue(5,2,29);
        persistValueCollfactClassifvalue(6,1,22);   // Пока что 22 вместо 19
        persistValueCollfactClassifvalue(6,2,32);
    }

    private void persistValueCollfactClassifvalue(Integer collId, Integer classifId, Integer classifValueId) {
        CollfactClassifvalue collfactClassifvalue = new CollfactClassifvalue(collId, classifId, classifValueId);
        collfactClassifvalueDAO.persist(collfactClassifvalue);
    }

    private void persistCollection() {
        List<String> vList = Arrays.asList("базис", "предел", "монотонная последовательность", "теорема", "доказательство", "следствие");
        for (String value: vList){
            persistValueCollection(value);
        }
    }

    private void persistValueCollection(String collectionName) {
        FactCollection factCollection = new FactCollection();
        factCollection.setFactcollName(collectionName);
        factCollectionDAO.persist(factCollection);
    }

    private void persistClassifierValue() {
        persistValueClassifierValue(1, null, "Курс математического анализа");
        persistValueClassifierValue(1, 1, "Глава 1 Дифференциальное исчисление функций одной переменной");
        persistValueClassifierValue(1, 2, "§ 1. Множества и функции. Логические символы");
        persistValueClassifierValue(1, 3, "1.1. Множества. Операции над множествами");
        persistValueClassifierValue(1, 3, "1.2. Функции");
        persistValueClassifierValue(1, 3, "1.3. Конечные множества и натуральные числа.");
        persistValueClassifierValue(1, 3, "1.4. Группировки элементов конечного множества");
        persistValueClassifierValue(1, 3, "1.5. Логические символы");
        persistValueClassifierValue(1, 2, "§ 2. Действительные числа");
        persistValueClassifierValue(1, 9, "2.1. Свойства действительных чисел");
        persistValueClassifierValue(1, 9, "2.2. Свойства сложения и умножения");
        persistValueClassifierValue(1, 9, "2.3. Свойства упорядоченности");
        persistValueClassifierValue(1, 9, "2.4. Свойство непрерывности действительных чисел");
        persistValueClassifierValue(1, 9, "2.5. Сечения в множестве действительных чисел");
        persistValueClassifierValue(1, 9, "2.6. Рациональные степени действительных чисел");
        persistValueClassifierValue(1, 9, "2.7. Формула бинома Ньютона");
        persistValueClassifierValue(1, 2, "§ 4. Предел числовой последовательности");
        persistValueClassifierValue(1, 17, "4.1. Определение предела числовой последовательности");
        persistValueClassifierValue(1, 17, "4.2. Единственность предела числовой последовательности");
        persistValueClassifierValue(1, 17, "4.3. Переход к пределу в неравенствах");
        persistValueClassifierValue(1, 17, "4.4. Ограниченность сходящихся последовательностей");
        persistValueClassifierValue(1, 17, "4.5. Монотонные последовательности");
        persistValueClassifierValue(1, 17, "4.6. Теорема Больцано—Вейерштрасса");
        persistValueClassifierValue(1, 17, "4.7. Критерий Коши сходимости последовательности");
        persistValueClassifierValue(1, 17, "4.8. Бесконечно малые последовательности");
        persistValueClassifierValue(2, null, "Определение");
        persistValueClassifierValue(2, null, "Лемма");
        persistValueClassifierValue(2, null, "Теорема");
        persistValueClassifierValue(2, null, "Доказательство");
        persistValueClassifierValue(2, null, "Пример");
        persistValueClassifierValue(2, null, "Замечание");
        persistValueClassifierValue(2, null, "Следствие");
        persistValueClassifierValue(2, null, "Вступлние");
        persistValueClassifierValue(2, null, "Вывод");
        persistValueClassifierValue(3, null, "легкая");
        persistValueClassifierValue(3, null, "средняя");
        persistValueClassifierValue(3, null, "сложная");
    }

    private void persistClassifier() {
        List<String> vList = Arrays.asList("Предметный классификатор", "Тип факта", "Сложность факта");
        for (String value: vList){
            persistValueClassifier(value);
        }
    }

    private void persistValueClassifierValue(Integer classifid, Integer parentId, String value) {
        ClassifierValue classifierValue = new ClassifierValue();
        classifierValue.setClassifier(classifierDAO.getClassifierById(classifid));
        classifierValue.setParentid(parentId);
        classifierValue.setValue(value);
        classifierValueDAO.persist(classifierValue);
    }

    private void persistValueClassifier(String value) {
        Classifier classifier = new Classifier();
        classifier.setClassifName(value);
        classifierDAO.persist(classifier);
    }
}