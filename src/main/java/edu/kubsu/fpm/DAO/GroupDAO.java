package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Group;

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
public class GroupDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public Group getGroupsById(Integer id){
        return (Group) em.createQuery("from Group where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Group group){
        em.persist(group);
    }
}
