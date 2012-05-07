package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Lection;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 27.04.12
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class LectionDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public List<Lection> findLectionsByCourseVarId(int courseVarId){
        List<Lection> lections = em.createQuery(
                "select cv.lectionList from Course_variation  cv where cv.id = :id").
                setParameter("id",courseVarId).getResultList();
        return lections;
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Lection lection){
        em.merge(lection);
    }

    public Lection findById(Integer id){
        return em.find(Lection.class, id);
    }
    
    public Lection getLectionByName(String name){
        return (Lection) em.createQuery("from Lection l where l.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

}
