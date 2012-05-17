package edu.kubsu.fpm.test;

import edu.kubsu.fpm.DAO.MessageDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Message;
import edu.kubsu.fpm.entity.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Разработчик: Искрич Андрей В. <a href="mailto:aviskrich@gmail.com">aviskrich@gmail.com</a>)
 * Дата создания: 4/19/12
 * Время создания: 2:03 PM
 */

@Stateless
public class MessageTest {
    @EJB
    private PersonDAO personDAO;

    @EJB
    private MessageDAO messageDAO;

    public void createTestUsers() {
        Person person1 = new Person();
        person1.setName("Andrey");
        person1.setSurname("Iskrich");

        Person person2 = new Person();
        person2.setName("Anna");
        person2.setSurname("Zhulanova");

        Message message1 = createMessagesForPerson(person1, person2);
        Message message2 = createMessagesForPerson(person2, person1);

        messageDAO.persist(message1);
        messageDAO.persist(message2);

    }

    private Message createMessagesForPerson(Person personFrom, Person personTo) {
        Message message = new Message();
        List<Person> receipientsForMessage1 = new ArrayList<Person>();
        receipientsForMessage1.add(personTo);
        message.setContent("Это первое сообщения для " + personTo.getName());
        message.setRecipients(receipientsForMessage1);
        message.setSender(personFrom);
        message.setMessageDate(new Date());

        return message;
    }

}
