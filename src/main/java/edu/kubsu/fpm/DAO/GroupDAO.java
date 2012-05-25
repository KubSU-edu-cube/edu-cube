package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Course_variation;
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
        return em.find(Group.class, id);
    }

    public Group getGroupByCourseVar(Course_variation course_variation){
        return (Group) em.createQuery("from Group g where g.courseVariation = :course")
                .setParameter("course", course_variation)
                .getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Group group){
        em.merge(group);
    }

    public Course_variation getCourseVarByGroupId(Integer id) {
        return (Course_variation) em.createQuery("select g.courseVariation from Group g where g.id = :id")
                .setParameter("id", id)
                .getSingleResult();

    }

    public Course_variation getCourseByGroupId(Integer groupId) {
        return (Course_variation) em.createQuery("select g.courseVariation from Group g where g.id = :id")
                .setParameter("id", groupId)
                .getSingleResult();
    }
}
