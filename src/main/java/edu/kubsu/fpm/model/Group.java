/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Марина
 */
@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "course_id")
    private Integer courseId;
//    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")     // , nullable = false
//    @ManyToOne(optional = false)
//    private Course_variation courseVariation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Collection<AdditionalQuestion> aditionalQuestionCollection;

    public Group() {
    }

    public Group(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Course_variation getCourseVariation() {
//        return courseVariation;
//    }
//
//    public void setCourseVariation(Course_variation courseVariation) {
//        this.courseVariation = courseVariation;
//    }

    public Collection<AdditionalQuestion> getAditionalQuestionCollection() {
        return aditionalQuestionCollection;
    }

    public void setAditionalQuestionCollection(Collection<AdditionalQuestion> aditionalQuestionCollection) {
        this.aditionalQuestionCollection = aditionalQuestionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.Group[id=" + id + "]";
    }

}
