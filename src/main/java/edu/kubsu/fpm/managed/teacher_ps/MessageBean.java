package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.entity.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Разработчик: Искрич Андрей В. <a href="mailto:aviskrich@gmail.com">aviskrich@gmail.com</a>)
 * Дата создания: 4/12/12
 * Время создания: 10:57 AM
 */
@ManagedBean
@ViewScoped
public class MessageBean {

    private String teacherId;

    public MessageBean() {
        this.setTeacherId((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId"));

    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    private List<Message> messages = new ArrayList<Message>();



    public List<Message> getMessages() {
        return messages;
    }
}
