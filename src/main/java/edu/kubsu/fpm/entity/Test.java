package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 9:56
 */

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JoinColumn(name = "lection_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Lection lection;

    @JoinColumn(name = "test_type_id", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private TestType type;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Task> taskList;

    public Test() {
    }

    public Test(Lection lection, TestType type) {
        this.lection = lection;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lection getLection() {
        return lection;
    }

    public void setLection(Lection lection) {
        this.lection = lection;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
