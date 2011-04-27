/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.work_with_persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import edu.kubsu.fpm.model.*;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;



/**
 *
 * @author Марина
 */
@ManagedBean
@SessionScoped
public class classifManagedBean implements Serializable {

    private String newClassifName;
    private String newfactcollName;
    private List listClassifName = new ArrayList();
    private List listfactcollName = new ArrayList();

    public classifManagedBean() {
    }

    //    Добавляет новые классификаторы
    public String addClassif(){
//        Получаем экземпляр класса с заданными значениями
        Classifier classif = new Classifier(newClassifName);
        newClassifName = new String();
//        Теперь listClassifName содержит заданное значение
        listClassifName.add(classif);
        return "example";
    }

    //    Добавляет новые названия коллекций фактов
    public String addFactcol(){
//        Получаем экземпляр класса с заданными значениями
        FactCollection f_coll = new FactCollection(newfactcollName);
        newfactcollName = new String();
//        Теперь listfactcollName содержит заданное значение
        listfactcollName.add(f_coll);
        return "example";
    }

    public List getListClassifName() {
        return listClassifName;
    }

    public void setListClassifName(List listClassifName) {
        this.listClassifName = listClassifName;
    }

    public List getListfactcollName() {
        return listfactcollName;
    }

    public void setListfactcollName(List listfactcollName) {
        this.listfactcollName = listfactcollName;
    }

    public String getNewClassifName() {
        return newClassifName;
    }

    public void setNewClassifName(String newClassifName) {
        this.newClassifName = newClassifName;
    }

    public String getNewfactcollName() {
        return newfactcollName;
    }

    public void setNewfactcollName(String newfactcollName) {
        this.newfactcollName = newfactcollName;
    }


}
