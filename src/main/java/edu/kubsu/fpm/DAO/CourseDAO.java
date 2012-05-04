package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Course;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 25.04.12
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class CourseDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Course course){
        em.merge(course);
    }

    public Course getCourseById(int id){
        return (Course) em.createQuery("from Course c where c.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
