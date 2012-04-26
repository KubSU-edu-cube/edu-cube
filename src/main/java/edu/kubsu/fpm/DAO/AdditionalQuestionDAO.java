package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.AdditionalQuestion;

import javax.ejb.*;
import javax.persistence.EntityManager;
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

    public Integer getPercentObligatoryQuestion(int groupId, int classifValuesId){
        return (Integer) em.createQuery("select distinct percentObligatoryQuestion from AdditionalQuestion " +
                "where classifValuesid = :classifId and groupid = :groupId")
                .setParameter("classifId", classifValuesId)
                .setParameter("groupId", groupId)
                .getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(AdditionalQuestion additionalQuestion){
        em.persist(additionalQuestion);
    }
}
