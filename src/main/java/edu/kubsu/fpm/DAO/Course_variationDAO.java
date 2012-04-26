package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Course;
import edu.kubsu.fpm.entity.Course_variation;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 25.04.12
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class Course_variationDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Course_variation course_variation){
        em.persist(course_variation);
    }
}
