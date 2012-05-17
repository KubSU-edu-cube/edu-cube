package edu.kubsu.fpm.DAO;

import edu.kubsu.fpm.entity.Message;
import edu.kubsu.fpm.entity.Person;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Разработчик: Искрич Андрей В. <a href="mailto:aviskrich@gmail.com">aviskrich@gmail.com</a>)
 * Дата создания: 4/19/12
 * Время создания: 2:46 PM
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class MessageDAO {
    @PersistenceContext(unitName = "sample")
    EntityManager em;

    public List<Message> getIncomingMessages(Person person) {
        return (List<Message>) em.createQuery("select m from Message m where :person in (m.recipients) and m.messageDate between :dateFrom and :dateAfter").setParameter("person", person).getResultList();

    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void persist(Message message) {
        em.merge(message);
    }
}
