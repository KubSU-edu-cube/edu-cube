package edu.kubsu.fpm.entity;

import javax.persistence.*;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 10:50
 */

@Entity
public class AllAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JoinColumn(name = "Task_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Task task;

    @JoinColumn(name = "answer_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Answer answer;

    public AllAnswers() {
    }

    public AllAnswers(Task task, Answer answer) {
        this.task = task;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
