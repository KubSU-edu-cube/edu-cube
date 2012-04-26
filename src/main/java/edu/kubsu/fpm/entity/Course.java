package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 25.04.12
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Course_variation> variationList;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Lection> lections;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course_variation> getVariationList() {
        return variationList;
    }

    public void setVariationList(List<Course_variation> variationList) {
        this.variationList = variationList;
    }

    public List<Lection> getLections() {
        return lections;
    }

    public void setLections(List<Lection> lections) {
        this.lections = lections;
    }
}
