package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Department;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 10.04.12
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DepartmentDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Department department){
        em.persist(department);
    }

}
