package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.AllAnswers;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 07.05.12
 * Time: 21:06
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class AllAnswersDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(AllAnswers answers){
        em.persist(answers);
    }
}
