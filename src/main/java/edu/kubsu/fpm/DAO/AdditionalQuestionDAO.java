package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Groups;
import edu.kubsu.fpm.model.AdditionalQuestion;
import edu.kubsu.fpm.model.ClassifierValue;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<AdditionalQuestion> getAddQuestByGroup(Groups group){
        return (List<AdditionalQuestion>) em.createQuery("from AdditionalQuestion where group = :group")
                .setParameter("group", group)
                .getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(AdditionalQuestion additionalQuestion){
        em.persist(additionalQuestion);
    }
}
