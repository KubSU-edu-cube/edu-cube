package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Groups;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Marina
 * Date: 24.04.12
 * Time: 13:30
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class GroupsDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public Groups getGroupsById(Integer id){
        return (Groups) em.createNamedQuery("Groups.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    public Integer getClassiferValuesById(int idGroup){
        return (Integer) em.createQuery("select classifValuesid from Groups where id = :id")
                .setParameter("id", idGroup)
                .getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Groups groups){
        em.persist(groups);
    }
}
