package edu.kubsu.fpm.entity;

import javax.ejb.EJB;
import javax.persistence.*;
import javax.xml.rpc.encoding.SerializationContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 08.04.12
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Education_status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    
    private int mode;

    @OneToMany(mappedBy = "educationStatus")
    private List<Education> educations;

    public Education_status() {
    }

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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }
}
