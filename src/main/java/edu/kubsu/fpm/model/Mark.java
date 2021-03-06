///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package edu.kubsu.fpm.model;
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//
///**
// *
// * @author Марина
// */
//@Entity
//@Table(name = "MARK")
//@NamedQueries({
//    @NamedQuery(name = "Mark.findAll", query = "SELECT m FROM Mark m"),
//    @NamedQuery(name = "Mark.findById", query = "SELECT m FROM Mark m WHERE m.id = :id"),
//    @NamedQuery(name = "Mark.findByMark", query = "SELECT m FROM Mark m WHERE m.mark = :mark")})
//public class Mark implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "ID", nullable = false)
//    private Integer id;
//    @Basic(optional = false)
//    @Column(name = "MARK", nullable = false)
//    private int mark;
//    @JoinColumn(name = "STUDENTID", referencedColumnName = "ID", nullable = false)
//    @ManyToOne(optional = false)
//    private Student studentid;
//    @JoinColumn(name = "CLASSIF_VALUESID", referencedColumnName = "ID", nullable = false)
//    @ManyToOne(optional = false)
//    private ClassifierValue classifValuesid;
//
//    public Mark() {
//    }
//
//    public Mark(Integer id) {
//        this.id = id;
//    }
//
//    public Mark(Integer id, int mark) {
//        this.id = id;
//        this.mark = mark;
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
//    public int getMark() {
//        return mark;
//    }
//
//    public void setMark(int mark) {
//        this.mark = mark;
//    }
//
//    public Student getStudentid() {
//        return studentid;
//    }
//
//    public void setStudentid(Student studentid) {
//        this.studentid = studentid;
//    }
//
//    public ClassifierValue getClassifValuesid() {
//        return classifValuesid;
//    }
//
//    public void setClassifValuesid(ClassifierValue classifValuesid) {
//        this.classifValuesid = classifValuesid;
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
//        if (!(object instanceof Mark)) {
//            return false;
//        }
//        Mark other = (Mark) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "edu.kubsu.fpm.model.Mark[id=" + id + "]";
//    }
//
//}
