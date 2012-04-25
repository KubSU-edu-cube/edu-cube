package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.AditionalQuestion;

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

//    public  getAditionalQuestion

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(AditionalQuestion aditionalQuestion){
        em.persist(aditionalQuestion);
    }
}
