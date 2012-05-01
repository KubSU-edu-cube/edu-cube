package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Groups;
import edu.kubsu.fpm.model.ClassifierValue;

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
        return (Groups) em.createQuery("from Groups where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public ClassifierValue getClassiferValuesById(int idGroup){
        return (ClassifierValue ) em.createQuery("select g.classifValues from Groups g where g.id = :idGroup")
                .setParameter("idGroup", idGroup)
                .getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Groups groups){
        em.persist(groups);
    }
}
