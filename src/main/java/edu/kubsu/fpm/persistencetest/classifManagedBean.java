/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.persistencetest;

import edu.kubsu.fpm.model.Classifier;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Марина
 */
@ManagedBean
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class classifManagedBean implements Serializable {

    private String newClassifName;
    private String newfactcollName;
    private List listClassifName = new ArrayList();
    private List listfactcollName = new ArrayList();

    @PersistenceContext(unitName = "educube")
    private EntityManager em;

    public classifManagedBean() {
    }

    //    Добавляет новые классификаторы
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String addClassif() {
        //        Получаем экземпляр класса с заданными значениями
        Classifier classif = new Classifier(newClassifName);
        newClassifName = "";
        //        Записываем значение в базу

        em.persist(classif);
        return "index";
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
