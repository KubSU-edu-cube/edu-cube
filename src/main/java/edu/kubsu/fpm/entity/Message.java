package edu.kubsu.fpm.entity;

import org.jboss.logging.Field;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrey
 * Date: 4/11/12
 * Time: 1:02 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "previous_msg_id", referencedColumnName = "id")
    private Message previousMessage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "sender_id", referencedColumnName = "id")
    private Person sender;

    @ManyToMany(targetEntity = edu.kubsu.fpm.entity.Person.class)
    private List<Person> recipients;

    @ManyToOne
    @JoinColumn(columnDefinition = "message_type", referencedColumnName = "id")
    private MessageType messageType;

    private String content;
    private Date messageDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Message getPreviousMessage() {
        return previousMessage;
    }

    public void setPreviousMessage(Message previousMessage) {
        this.previousMessage = previousMessage;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public List<Person> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Person> recipients) {
        this.recipients = recipients;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}
