package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Education;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 08.05.12
 * Time: 13:49
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EducationDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Education education){
        em.persist(education);
    }
}
