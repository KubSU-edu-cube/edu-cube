package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Fact;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 19.04.12
 * Time: 20:26
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class FactDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;
    
    public Integer getObligitaryFactById(Integer idFact){
        return (Integer) em.createQuery("SELECT obligatory FROM Fact WHERE id = :id")
                .setParameter("id", idFact)
                .getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Fact fact){
        em.persist(fact);
    }
}
