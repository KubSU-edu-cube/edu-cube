package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Person;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: andrey
 * Date: 4/11/12
 * Time: 1:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class PersonDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public Person getPersonById(Integer id) {
        return em.find(Person.class, id);
    }

    public void refresh(Person person){
        em.refresh(person);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Person person){
        em.persist(person);
    }
}
