package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Task;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 23:10
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TaskDAO {

    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Task task){
        em.persist(task);
    }
}
