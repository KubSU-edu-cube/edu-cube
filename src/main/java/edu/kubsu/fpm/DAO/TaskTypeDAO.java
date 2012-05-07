package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.TaskType;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 16:57
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TaskTypeDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(TaskType taskType){
        em.persist(taskType);
    }

    public TaskType findByType(String type){
        return (TaskType) em.createQuery("from TaskType tt where tt.taskType = :type")
                .setParameter("type", type)
                .getSingleResult();
    }
}
