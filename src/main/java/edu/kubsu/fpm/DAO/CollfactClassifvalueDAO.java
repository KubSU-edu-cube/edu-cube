package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.Classifier;
import edu.kubsu.fpm.model.ClassifierValue;
import edu.kubsu.fpm.model.CollfactClassifvalue;
import edu.kubsu.fpm.model.FactCollection;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * User: Marina
 * Date: 19.04.12
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class CollfactClassifvalueDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(CollfactClassifvalue collfactClassifvalue){
        em.merge(collfactClassifvalue);
    }

    public ClassifierValue getClassifValueByCollectionAndClassif(FactCollection factCollection, Classifier classifier) {
        return (ClassifierValue) em.createQuery("select cfcv.classifierValue from CollfactClassifvalue cfcv " +
                "where cfcv.factCollection = :coll and cfcv.classifier = :classifier")
                .setParameter("coll", factCollection)
                .setParameter("classifier", classifier)
                .getSingleResult();
    }
}
