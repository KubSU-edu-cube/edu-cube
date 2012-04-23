package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Classifier;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * User: Marina
 * Date: 19.04.12
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class ClassifierDAO {
    @PersistenceContext(unitName = "sample")
    private EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Classifier classifier) {
        em.persist(classifier);
    }

    public Classifier getClassifierById(Integer classifId) {
        return (Classifier) em.createNamedQuery("Classifier.findById")
                .setParameter("id", classifId)
                .getSingleResult();
    }
}
