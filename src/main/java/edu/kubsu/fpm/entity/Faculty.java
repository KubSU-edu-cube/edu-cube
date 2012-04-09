package edu.kubsu.fpm.entity;

import javax.ejb.EJB;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 08.04.12
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Faculty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;


    @OneToMany(mappedBy = "faculty")
    private List<Department> departments;

    @ManyToOne
    @JoinColumn(name="university_id", referencedColumnName = "id")
    private University university;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
