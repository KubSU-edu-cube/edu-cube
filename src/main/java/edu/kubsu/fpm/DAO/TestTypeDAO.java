package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.TestType;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 16:53
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TestTypeDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(TestType testType){
        em.persist(testType);
    }

    public TestType findByName(String name){
        return (TestType) em.createQuery("from TestType tt where tt.type = :type")
                .setParameter("type", name)
                .getSingleResult();
    }
}
