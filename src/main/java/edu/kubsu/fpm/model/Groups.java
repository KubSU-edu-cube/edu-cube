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
//@Table(name = "GROUPS")
//@NamedQueries({
//    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
//    @NamedQuery(name = "Groups.findById", query = "SELECT g FROM Groups g WHERE g.id = :id"),
//    @NamedQuery(name = "Groups.findByGroupName", query = "SELECT g FROM Groups g WHERE g.groupName = :groupName")})
//public class Groups implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "ID", nullable = false)
//    private Integer id;
//    @Basic(optional = false)
//    @Column(name = "GROUP_NAME", nullable = false, length = 200)
//    private String groupName;
//    @JoinColumn(name = "CLASSIF_VALUESID", referencedColumnName = "ID", nullable = false)
//    @ManyToOne(optional = false)
//    private ClassifierValue classifValuesid;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupid")
//    private Collection<AditionalQuestion> aditionalQuestionCollection;
//
//    public Groups() {
//    }
//
//    public Groups(Integer id) {
//        this.id = id;
//    }
//
//    public Groups(Integer id, String groupName) {
//        this.id = id;
//        this.groupName = groupName;
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
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
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
//    public Collection<AditionalQuestion> getAditionalQuestionCollection() {
//        return aditionalQuestionCollection;
//    }
//
//    public void setAditionalQuestionCollection(Collection<AditionalQuestion> aditionalQuestionCollection) {
//        this.aditionalQuestionCollection = aditionalQuestionCollection;
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
//        if (!(object instanceof Groups)) {
//            return false;
//        }
//        Groups other = (Groups) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "edu.kubsu.fpm.model.Groups[id=" + id + "]";
//    }
//
//}
