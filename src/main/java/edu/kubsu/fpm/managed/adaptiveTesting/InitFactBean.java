package edu.kubsu.fpm.managed.adaptiveTesting;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.model.Classifier;
import edu.kubsu.fpm.model.ClassifierValue;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.FileNotFoundException;
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

//    @EJB
//    private FactCollectionDAO factCollectionDAO;
//
//    @EJB
//    private CollfactClassifvalueDAO collfactClassifvalueDAO;
//
//    @EJB
//    private FactDAO factDAO;
//
//    @EJB
//    private SynAntDAO synAntDAO;

    public InitFactBean(){

    }

    public void tmpFactsInit(){
        persistClassifier();
        persistClassifierValue();
//        persistCollection();
//        persistCollfactClassifvalue();
//        try{
//            persistFact();
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        persistSynAnt();
    }

    private void persistSynAnt() {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void persistFact() throws FileNotFoundException {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void persistCollfactClassifvalue() {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void persistCollection() {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void persistClassifierValue() {
        persistValueClassifierValue(1, null, "Курс математического анализа");
//        persistValueClassifierValue(1, 1, "Глава 1 Дифференциальное исчисление функций одной переменной");
//        persistValueClassifierValue(1, 2, "§ 1. Множества и функции. Логические символы");
//        persistValueClassifierValue(1, 3, "1.1. Множества. Операции над множествами");
//        persistValueClassifierValue(1, 3, "1.2. Функции");
//        persistValueClassifierValue(1, 3, "1.3. Конечные множества и натуральные числа.");
//        persistValueClassifierValue(1, 3, "1.4. Группировки элементов конечного множества");
//        persistValueClassifierValue(1, 3, "1.5. Логические символы");
//        persistValueClassifierValue(1, 2, "§ 2. Действительные числа");
//        persistValueClassifierValue(1, 9, "2.1. Свойства действительных чисел");
//        persistValueClassifierValue(1, 9, "2.2. Свойства сложения и умножения");
//        persistValueClassifierValue(1, 9, "2.3. Свойства упорядоченности");
//        persistValueClassifierValue(1, 9, "2.4. Свойство непрерывности действительных чисел");
//        persistValueClassifierValue(1, 9, "2.5. Сечения в множестве действительных чисел");
//        persistValueClassifierValue(1, 9, "2.6. Рациональные степени действительных чисел");
//        persistValueClassifierValue(1, 9, "2.7. Формула бинома Ньютона");
//        persistValueClassifierValue(1, 2, "§ 4. Предел числовой последовательности");
//        persistValueClassifierValue(1, 17, "4.1. Определение предела числовой последовательности");
//        persistValueClassifierValue(1, 17, "4.2. Единственность предела числовой последовательности");
//        persistValueClassifierValue(1, 17, "4.3. Переход к пределу в неравенствах");
//        persistValueClassifierValue(1, 17, "4.4. Ограниченность сходящихся последовательностей");
//        persistValueClassifierValue(1, 17, "4.5. Монотонные последовательности");
//        persistValueClassifierValue(1, 17, "4.6. Теорема Больцано—Вейерштрасса");
//        persistValueClassifierValue(1, 17, "4.7. Критерий Коши сходимости последовательности");
//        persistValueClassifierValue(1, 17, "4.8. Бесконечно малые последовательности");
//        persistValueClassifierValue(2, null, "Определение");
//        persistValueClassifierValue(2, null, "Лемма");
//        persistValueClassifierValue(2, null, "Теорема");
//        persistValueClassifierValue(2, null, "Доказательство");
//        persistValueClassifierValue(2, null, "Пример");
//        persistValueClassifierValue(2, null, "Замечание");
//        persistValueClassifierValue(2, null, "Следствие");
//        persistValueClassifierValue(2, null, "Вступлние");
//        persistValueClassifierValue(2, null, "Вывод");
//        persistValueClassifierValue(3, null, "легкая");
//        persistValueClassifierValue(3, null, "средняя");
//        persistValueClassifierValue(3, null, "сложная");
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
