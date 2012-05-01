package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.SynAnt;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 19.04.12
 * Time: 21:26
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class SynAntDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(SynAnt synAnt){
        em.persist(synAnt);
    }
}
