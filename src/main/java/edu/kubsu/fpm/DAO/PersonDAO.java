package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.managed.classes.DateConverter;
import edu.kubsu.fpm.model.Group;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Person> fullSearch(String firstName,
                                   String secondName,
                                   int fromAge,
                                   int toAge,
                                   String city,
                                   String country,
                                   String sex){

        String cityParam = (city == "" ? "%":city);
        String countryParam = (country == "" ? "%" :country);
        String sexParam = (sex.toUpperCase().equals("любой".toUpperCase())? "%":sex);
        Date dateFromParam = (fromAge == 0 ? DateConverter.getMinDate():DateConverter.getDate(DateConverter.getCurDate(),fromAge));
        Date dateToParam = (toAge == 0 ? DateConverter.getMaxDate():DateConverter.getDate(DateConverter.getCurDate(),toAge));

        return (List<Person>)em.createQuery("from Person p where " +
                "((upper(p.name) like upper(:firstName)and(upper(p.surname) like upper(:secondName))) " +
                "or" +
                " (upper(p.surname) like upper(:firstName)and upper(p.name)like upper(:secondName)))" +
                "and" +
                " upper(p.currentCity) like upper(:cityParam)" +
                "and " +
                "upper(p.currentCountry) like upper(:countryParam)" +
                "and " +
                "upper(p.sex) like upper(:sexParam) " +
                "and " +
                " p.dateOfBirth between :dateToParam and :dateFromParam").
                setParameter("firstName",firstName).
                setParameter("secondName", secondName).
                setParameter("cityParam", cityParam).
                setParameter("countryParam", countryParam).
                setParameter("sexParam", sexParam).
                setParameter("dateFromParam", dateFromParam).
                setParameter("dateToParam", dateToParam).
                getResultList();

}

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Person person){
        em.merge(person);
    }
    
    public List<Group> getGroupListByPersonId(int id){
        try{
            return (List<Group>) em.createQuery("select p.groupList from Person p where p.id = :id")
                    .setParameter("id", id)
                    .getResultList();
        } catch (NoResultException e){
            return new ArrayList<>();
        }
    }
}
