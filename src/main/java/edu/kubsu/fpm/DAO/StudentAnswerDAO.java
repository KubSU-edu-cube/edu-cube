package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.entity.StudentAnswer;
import edu.kubsu.fpm.entity.Task;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 09.05.12
 * Time: 17:18
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class StudentAnswerDAO {

    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(StudentAnswer answer){
        em.merge(answer);
    }

    public StudentAnswer getAnswerByTaskAndPerson(Person person, Task task){
        return (StudentAnswer) em.createQuery("from StudentAnswer sa where sa.person = :person and sa.task = :task")
                .setParameter("person", person)
                .setParameter("task", task)
                .getSingleResult();
    }
}
