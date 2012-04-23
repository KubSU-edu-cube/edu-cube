package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Words;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 22.04.12
 * Time: 23:13
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class WordsDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Words words){
        em.persist(words);
    }
}
