/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.work_with_persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import edu.kubsu.fpm.model.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;



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
    @PersistenceContext(unitName = "educube")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    public classifManagedBean() {
    }

    //    Добавляет новые классификаторы
    public String addClassif(){
        try {
            //        Начинаем транзакцию
            utx.begin();
            //        Получаем экземпляр класса с заданными значениями
            Classifier classif = new Classifier(newClassifName);
            newClassifName = "";
            //        Записываем значение в базу

            em.persist(classif);
            //        Заканчиваем транзакцию
            utx.commit();
        } catch (RollbackException ex) {
            Logger.getLogger(classifManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(classifManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(classifManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(classifManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(classifManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(classifManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(classifManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "work_with_bd";
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
