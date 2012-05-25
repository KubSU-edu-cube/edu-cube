/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import edu.kubsu.fpm.entity.Lection;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.entity.Test;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Марина
 */
@Entity
public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double mark;

    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Person student;

    @JoinColumn(name = "TEST_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Test test;

    @JoinColumn(name = "LECTION_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Lection lection;

    public Mark() {
    }

    public Mark(Integer id) {
        this.id = id;
    }

    public Mark(Integer id, Double mark) {
        this.id = id;
        this.mark = mark;
    }

    public Lection getLection() {
        return lection;
    }

    public void setLection(Lection lection) {
        this.lection = lection;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

}
