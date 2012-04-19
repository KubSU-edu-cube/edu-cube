package edu.kubsu.fpm.managed;

import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Message;
import edu.kubsu.fpm.entity.Person;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrey
 * Date: 4/11/12
 * Time: 1:36 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class MessagingBean {
    //@todo Заменить эту константу на данные из авторизации пользователя
    public static final int userId = 1;
    private Person person;

    @EJB
    private PersonDAO personDAO;

    /**
     * Передача в метод идентификатора пользователя исключается, чтобы не позволить пользователям читать чужие сообщения
     * @return список сообщений пользователя
     */
    public List<Message> getOutcomingMessages() {
        if (person == null) {
            person = personDAO.getPersonById(userId);
        } else {
            personDAO.refresh(person);
        }
        return person.getMessages();
    }


}
