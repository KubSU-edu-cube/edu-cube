package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 10:09
 */

@Entity
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String taskType;

    @OneToMany(mappedBy = "taskType", cascade = CascadeType.ALL)
    private List<Task> taskList;

    public TaskType() {
    }

    public TaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
