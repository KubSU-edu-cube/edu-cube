package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.AllAnswers;
import edu.kubsu.fpm.entity.Answer;
import edu.kubsu.fpm.entity.Task;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Marina
 * Date: 07.05.12
 * Time: 21:06
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class AllAnswersDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(AllAnswers answers){
        em.persist(answers);
    }
    
    public List<Answer> getAnswersByTask(Task task){
        return (List<Answer>) em.createQuery("select aan.answer from AllAnswers aan where aan.task = :task")
                .setParameter("task", task)
                .getResultList();
    }
}
