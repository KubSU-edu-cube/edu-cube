package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.FactCollection;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * User: Marina
 * Date: 19.04.12
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class FactCollectionDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(FactCollection factCollection){
        em.persist(factCollection);
    }
}
