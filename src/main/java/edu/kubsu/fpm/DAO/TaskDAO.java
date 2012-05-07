package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Task;
import edu.kubsu.fpm.entity.Test;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public Task findById(Integer id) {
        return em.find(Task.class, id);
    }

    public void remove(int id) {
        Task task = findById(id);
        em.remove(task);
    }

    public List<Task> getTaskListByTest(Test test) {
        return em.createQuery("from Task t where t.test = :test")
                .setParameter("test", test)
                .getResultList();
    }
}
