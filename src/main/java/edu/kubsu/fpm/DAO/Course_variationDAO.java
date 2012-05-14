package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Course;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Lection;
import edu.kubsu.fpm.entity.Person;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
        em.merge(course_variation);
    }

    public Course_variation getCourseVarById(int id){
        return em.find(Course_variation.class, id);
    }

    public List<Course_variation> findByPersonId(int id){
        return (List<Course_variation>) em.createQuery("from Course_variation cv where cv.person.id = :id").setParameter("id",id).getResultList();
    }

    public Course_variation getCourseVarByPersonAndCourse(Person person, Course course){
        return (Course_variation) em.createQuery("from Course_variation cv where cv.person = :person and cv.course = :course")
                .setParameter("person", person)
                .setParameter("course", course)
                .getSingleResult();
    }
    
    public List<Course> getCourseByPerson(Person person){
        return (List<Course>) em.createQuery("select cv.course from Course_variation cv where cv.person = :person")
                .setParameter("person", person)
                .getResultList();
    }
    
    public List<Lection> getListLectionById(int id){
        return em.createQuery("select cv.lectionList from Course_variation cv where cv.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
    
    public List<Course_variation> getAll(){
        return em.createQuery("from Course_variation cv").getResultList();
    }
}
