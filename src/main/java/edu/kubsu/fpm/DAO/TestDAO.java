package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Lection;
import edu.kubsu.fpm.entity.Test;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 17:22
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TestDAO {

    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Test test){
        em.persist(test);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void remove(int pk){
        Test test = em.find(Test.class, pk);
        em.remove(test);
    }

    public List<Test> getAll(){
        return (List<Test>) em.createQuery("from Test").getResultList();
    }

}
