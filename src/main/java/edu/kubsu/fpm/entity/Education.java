package edu.kubsu.fpm.entity;

import javax.ejb.EJB;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 08.04.12
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Education implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "education_status_id", referencedColumnName = "id")
    private Education_status educationStatus;
    
    private Date enterDate;
    private Date graduateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Education_status getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(Education_status educationStatus) {
        this.educationStatus = educationStatus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }
}
