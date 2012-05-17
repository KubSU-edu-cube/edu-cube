package edu.kubsu.fpm.managed;

import edu.kubsu.fpm.test.MessageTest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.04.12
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@RequestScoped
public class TestBean implements Serializable {
    @EJB(beanName = "MessageTest", beanInterface = MessageTest.class)
    private MessageTest messageTest;

    public void callCreateTestUsers() {
        messageTest.createTestUsers();
    }
}
