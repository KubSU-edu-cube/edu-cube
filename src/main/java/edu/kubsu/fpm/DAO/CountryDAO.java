package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Country;
import edu.kubsu.fpm.entity.Department;
import edu.kubsu.fpm.entity.Faculty;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 12.04.12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class CountryDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Country country){
        em.persist(country);
    }
    public List<Country> getAllCountries() {
        return (List<Country>)em.createQuery("from Country").getResultList();
    }
    public Country find(int id){
        return em.find(Country.class, id);
    }

}
