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
@Table(name = "CLASSIFIER")
@NamedQueries({
    @NamedQuery(name = "Classifier.findAll", query = "SELECT c FROM Classifier c"),
    @NamedQuery(name = "Classifier.findById", query = "SELECT c FROM Classifier c WHERE c.id = :id"),
    @NamedQuery(name = "Classifier.findByClassifName", query = "SELECT c FROM Classifier c WHERE c.classifName = :classifName")})
public class Classifier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME", length = 300)
    private String classifName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifier")
    private Collection<FactClassifvalue> factClassifvalueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifier")
    private Collection<ClassifierValue> classifierValueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifier")
    private Collection<CollfactClassifvalue> collfactClassifvalueCollection;

    public Classifier() {
    }



    public Classifier(Integer id) {
        this.id = id;
    }

//    Конструктор для сохранения данных
    public Classifier(String newClassifName) {
        this.classifName = newClassifName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassifName() {
        return classifName;
    }

    public void setClassifName(String classifName) {
        this.classifName = classifName;
    }

    public Collection<FactClassifvalue> getFactClassifvalueCollection() {
        return factClassifvalueCollection;
    }

    public void setFactClassifvalueCollection(Collection<FactClassifvalue> factClassifvalueCollection) {
        this.factClassifvalueCollection = factClassifvalueCollection;
    }

    public Collection<ClassifierValue> getClassifierValueCollection() {
        return classifierValueCollection;
    }

    public void setClassifierValueCollection(Collection<ClassifierValue> classifierValueCollection) {
        this.classifierValueCollection = classifierValueCollection;
    }

    public Collection<CollfactClassifvalue> getCollfactClassifvalueCollection() {
        return collfactClassifvalueCollection;
    }

    public void setCollfactClassifvalueCollection(Collection<CollfactClassifvalue> collfactClassifvalueCollection) {
        this.collfactClassifvalueCollection = collfactClassifvalueCollection;
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
        if (!(object instanceof Classifier)) {
            return false;
        }
        Classifier other = (Classifier) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.Classifier[id=" + id + "]";
    }


}
