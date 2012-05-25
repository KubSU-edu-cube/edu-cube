package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.entity.Test;
import edu.kubsu.fpm.model.Mark;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Marina
 * Date: 24.05.12
 * Time: 21:34
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class MarkDAO {

    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Mark mark){
        em.persist(mark);
    }

    public List<Mark> getMarkListByPerson(Person person){
        return (List<Mark>) em.createQuery("from Mark m where m.student = :student")
                .setParameter("student", person)
                .getResultList();
    }

    public Mark getMarkByTest(Test test) {
        try{
            return (Mark) em.createQuery("from Mark m where m.test = :test")
                    .setParameter("test", test)
                    .getSingleResult();
        } catch (Exception e){
            return new Mark();
        }
    }
}
