package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Answer;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 07.05.12
 * Time: 20:58
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class AnswerDAO {

    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Answer answer){
        em.persist(answer);
    }
}
