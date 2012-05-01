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
@Table(name = "STUDENT")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name = "Student.findByStudentName", query = "SELECT s FROM Student s WHERE s.studentName = :studentName")
})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "STUDENT_NAME", nullable = false, length = 200)
    private String studentName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupid")
    private Collection<Student> studentCollection;
    @JoinColumn(name = "GROUPID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Student groupid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentid")
    private Collection<Mark> markCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentid")
    private Collection<Answer> answerCollection;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String studentName, String nickname, String family) {
        this.id = id;
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    public Student getGroupid() {
        return groupid;
    }

    public void setGroupid(Student groupid) {
        this.groupid = groupid;
    }

    public Collection<Mark> getMarkCollection() {
        return markCollection;
    }

    public void setMarkCollection(Collection<Mark> markCollection) {
        this.markCollection = markCollection;
    }

    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.Student[id=" + id + "]";
    }

}
