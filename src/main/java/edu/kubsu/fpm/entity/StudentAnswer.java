package edu.kubsu.fpm.entity;

import javax.persistence.*;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 10:40
 */

@Entity
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @JoinColumn(name = "student_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Person person;

    @JoinColumn(name = "task_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Task task;

    public StudentAnswer() {
    }

    public StudentAnswer(String content, Person person, Task task) {
        this.content = content;
        this.person = person;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
