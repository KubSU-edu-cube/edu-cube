package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Classifier;
import edu.kubsu.fpm.model.ClassifierValue;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Marina
 * Date: 19.04.12
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class ClassifierValueDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public List<ClassifierValue> getClassiferValueByClassifier(Classifier classifier){
        return (List<ClassifierValue>)em.createQuery("from ClassifierValue clv where clv.classifier = classifier").getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(ClassifierValue classifierValue){
        em.persist(classifierValue);
    }

    public ClassifierValue getClassifierValueById(Integer classifValueId) {
        return (ClassifierValue) em.createNamedQuery("ClassifierValue.findById")
                .setParameter("id", classifValueId)
                .getSingleResult();
    }
}
