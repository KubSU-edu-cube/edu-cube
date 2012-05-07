package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 9:54
 */

@Entity
public class TestType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Test> testList;

    public TestType() {
    }

    public TestType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
