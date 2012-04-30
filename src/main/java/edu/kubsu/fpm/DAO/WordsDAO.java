package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.model.SynAnt;
import edu.kubsu.fpm.model.Words;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Marina
 * Date: 22.04.12
 * Time: 23:13
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class WordsDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;
    
    public Words getWordsById(Integer id){
        return (Words) em.createNamedQuery("Words.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<SynAnt> getListSynAntByWord(String word){
        List<Object> results = em.createQuery("select distinct wd.synAntList, wd.synAntList from Words wd where wd.word = :word")
                .setParameter("word", word)
                .getResultList();

        return getSingleResult(results);
    }

    private List<SynAnt> getSingleResult(List<Object> results) {
        List<SynAnt> synAnts = new ArrayList<>();
        for (Object oRow : results) {
            Object[] r = (Object[]) oRow;
            if (!synAnts.contains(r[0]))
                synAnts.add((SynAnt) r[0]);
        }
        return synAnts;
    }

    public List<SynAnt> getListDependSynAntByWord(String word){
        List<Object> results = em.createQuery("select distinct wd.synAntCollection1, wd.synAntList from Words wd where wd.word = :word")
                .setParameter("word", word)
                .getResultList();

        return getSingleResult(results);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Words words){
        em.persist(words);
    }

}
