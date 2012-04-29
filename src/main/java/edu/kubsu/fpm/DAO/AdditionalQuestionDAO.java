package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.AdditionalQuestion;
import edu.kubsu.fpm.model.ClassifierValue;
import edu.kubsu.fpm.model.Groups;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 24.04.12
 * Time: 12:29
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class AdditionalQuestionDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public Integer getPercentObligatoryQuestion(Groups group, ClassifierValue classifValues){
        try{
            return (Integer) em.createQuery("select distinct aq.percentObligatoryQuestion from AdditionalQuestion aq " +
                    "where aq.classifValues = :classif and aq.group = :group")
                    .setParameter("classif", classifValues)
                    .setParameter("group", group)
                    .getSingleResult();
        } catch (NoResultException e){
            return 0;
        }

    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(AdditionalQuestion additionalQuestion){
        em.persist(additionalQuestion);
    }
}
