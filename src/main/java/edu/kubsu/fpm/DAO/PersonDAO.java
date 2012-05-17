package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Person;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public List<Person> findBy2Names(String firstName, String secondName){
        return (List<Person>)em.createQuery
                ("from Person p where (p.name = :firstName and p.surname = :secondName) or" +
                                        " (p.name = :secondName and p.surname = :firstName ) ").
                setParameter("secondName",secondName).
                setParameter("firstName",firstName).
                getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Person person){
        em.merge(person);
    }
}
