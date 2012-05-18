///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package edu.kubsu.fpm.model;
//
//import java.io.Serializable;
//import java.util.Collection;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
///**
// *
// * @author Марина
// */
//@Entity
//@Table(name = "STUDENT")
//@NamedQueries({
//    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
//    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
//    @NamedQuery(name = "Student.findByStudentName", query = "SELECT s FROM Student s WHERE s.studentName = :studentName"),
//    @NamedQuery(name = "Student.findByNickname", query = "SELECT s FROM Student s WHERE s.nickname = :nickname"),
//    @NamedQuery(name = "Student.findByFamily", query = "SELECT s FROM Student s WHERE s.family = :family")})
//public class Student implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "ID", nullable = false)
//    private Integer id;
//    @Basic(optional = false)
//    @Column(name = "STUDENT_NAME", nullable = false, length = 200)
//    private String studentName;
//    @Basic(optional = false)
//    @Column(name = "NICKNAME", nullable = false, length = 200)
//    private String nickname;
//    @Basic(optional = false)
//    @Column(name = "FAMILY", nullable = false, length = 200)
//    private String family;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupid")
//    private Collection<Student> studentCollection;
//    @JoinColumn(name = "GROUPID", referencedColumnName = "ID", nullable = false)
//    @ManyToOne(optional = false)
//    private Student groupid;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentid")
//    private Collection<Mark> markCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentid")
//    private Collection<Answer> answerCollection;
//
//    public Student() {
//    }
//
//    public Student(Integer id) {
//        this.id = id;
//    }
//
//    public Student(Integer id, String studentName, String nickname, String family) {
//        this.id = id;
//        this.studentName = studentName;
//        this.nickname = nickname;
//        this.family = family;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public String getFamily() {
//        return family;
//    }
//
//    public void setFamily(String family) {
//        this.family = family;
//    }
//
//    public Collection<Student> getStudentCollection() {
//        return studentCollection;
//    }
//
//    public void setStudentCollection(Collection<Student> studentCollection) {
//        this.studentCollection = studentCollection;
//    }
//
//    public Student getGroupid() {
//        return groupid;
//    }
//
//    public void setGroupid(Student groupid) {
//        this.groupid = groupid;
//    }
//
//    public Collection<Mark> getMarkCollection() {
//        return markCollection;
//    }
//
//    public void setMarkCollection(Collection<Mark> markCollection) {
//        this.markCollection = markCollection;
//    }
//
//    public Collection<Answer> getAnswerCollection() {
//        return answerCollection;
//    }
//
//    public void setAnswerCollection(Collection<Answer> answerCollection) {
//        this.answerCollection = answerCollection;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Student)) {
//            return false;
//        }
//        Student other = (Student) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "edu.kubsu.fpm.model.Student[id=" + id + "]";
//    }
//
//}
