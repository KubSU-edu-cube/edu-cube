package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.EstimationFunction;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Marina
 * Date: 01.05.12
 * Time: 22:03
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EstimationFunctionDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public List<EstimationFunction> getAll(){
        return (List<EstimationFunction>) em.createQuery("from EstimationFunction").getResultList();
    }
    
    public List<String> getAllEstFunction(){
        return (List<String>) em.createQuery("select ef.estFunction from EstimationFunction ef")
                .getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(EstimationFunction estimationFunction){
        em.persist(estimationFunction);
    }
    
    public Integer getFuncIdByName(Integer funcName){
        return (Integer) em.createQuery("select ef.id from EstimationFunction ef where ef.estFunction = :func")
                .setParameter("func", funcName)
                .getSingleResult();
    }
}
