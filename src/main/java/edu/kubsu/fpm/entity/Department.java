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
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    private List<Education> educations;
}
