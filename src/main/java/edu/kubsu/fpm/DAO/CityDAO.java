package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.City;
import edu.kubsu.fpm.entity.Country;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 12.04.12
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class CityDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(City city){
        em.persist(city);
    }

    public List<City> getCitiesByCountry(Country selectedCountry) {
        return (List<City>)em.createQuery
                ("from City c where c.country = :sCountry").
                setParameter("sCountry",selectedCountry).
                getResultList();
    }
}
