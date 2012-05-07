package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 10:11
 */

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @JoinColumn(name = "Test_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Test test;

    @JoinColumn(name = "task_type_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private TaskType taskType;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<StudentAnswer> studentAnswerList;
    
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<AllAnswers> answersList;

    public Task() {
    }

    public Task(String content, Test test, TaskType taskType) {
        this.content = content;
        this.test = test;
        this.taskType = taskType;
    }

    public List<AllAnswers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<AllAnswers> answersList) {
        this.answersList = answersList;
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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public List<StudentAnswer> getStudentAnswerList() {
        return studentAnswerList;
    }

    public void setStudentAnswerList(List<StudentAnswer> studentAnswerList) {
        this.studentAnswerList = studentAnswerList;
    }
}
