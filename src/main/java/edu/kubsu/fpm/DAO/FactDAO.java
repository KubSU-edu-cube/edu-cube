package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Fact;
import edu.kubsu.fpm.model.FactCollection;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    
    private List<Fact> getFactByCollection(FactCollection collection){
        return (List<Fact>)em.createQuery("from Fact f where f.collection = collection").getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Fact fact){
        em.persist(fact);
    }
}
