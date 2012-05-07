package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.EstimationFuncGroupPK;
import edu.kubsu.fpm.entity.EstimationFunc_Group;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Marina
 * Date: 02.05.12
 * Time: 12:56
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EstimationFunc_GroupDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public EstimationFunc_Group findByID(EstimationFuncGroupPK pk){
        return em.find(EstimationFunc_Group.class, pk);
    }

    public List<EstimationFunc_Group> getFunc_Group(){
        return (List<EstimationFunc_Group>) em.createQuery("from EstimationFunc_Group efg").getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void remove(EstimationFuncGroupPK pk){
        EstimationFunc_Group func_group = em.find(EstimationFunc_Group.class, pk);
        em.remove(func_group);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(EstimationFunc_Group func_group){
        em.persist(func_group);
    }
}
