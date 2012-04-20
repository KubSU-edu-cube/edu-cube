package edu.kubsu.fpm.DAO;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import edu.kubsu.fpm.model.Classifier;

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
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext(unitName = "sample")
    private EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Classifier classifier){
        em.persist(classifier);
    }

    public Classifier getClassifierById(Integer classifId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (Classifier)entityManager.createQuery("FROM Classifier c WHERE c.id = :id")
                .setParameter("id", classifId)
                .getSingleResult();
    }
}
