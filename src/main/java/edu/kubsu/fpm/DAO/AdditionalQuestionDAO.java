package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Group;
import edu.kubsu.fpm.model.AdditionalQuestion;

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

    public Integer getPercentObligatoryQuestion(Group group){
        try{
            return (Integer) em.createQuery("select distinct aq.percentObligatoryQuestion from AdditionalQuestion aq " +
                    "where aq.group = :group")
                    .setParameter("group", group)
                    .getSingleResult();
        } catch (NoResultException e){
            return 0;
        }
    }

    public AdditionalQuestion findByID(Integer pk){
        return em.find(AdditionalQuestion.class, pk);
    }

    public List<AdditionalQuestion> getAddQuestByGroup(Group group){
        return (List<AdditionalQuestion>) em.createQuery("from AdditionalQuestion where group = :group")
                .setParameter("group", group)
                .getResultList();
    }
    
    public List<AdditionalQuestion> getAll(){
        return (List<AdditionalQuestion>) em.createQuery("from AdditionalQuestion ad").getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(AdditionalQuestion additionalQuestion){
        em.persist(additionalQuestion);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void remove(int id){
        AdditionalQuestion additionalQuestion = em.find(AdditionalQuestion.class, id);
        em.remove(additionalQuestion);
    }
}
