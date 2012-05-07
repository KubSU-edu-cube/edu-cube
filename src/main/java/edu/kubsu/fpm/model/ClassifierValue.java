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
@Table(name = "CLASSIFIER_VALUE")
@NamedQueries({
    @NamedQuery(name = "ClassifierValue.findAll", query = "SELECT c FROM ClassifierValue c"),
    @NamedQuery(name = "ClassifierValue.findById", query = "SELECT c FROM ClassifierValue c WHERE c.id = :id"),
    @NamedQuery(name = "ClassifierValue.findByValue", query = "SELECT c FROM ClassifierValue c WHERE c.value = :value"),
    @NamedQuery(name = "ClassifierValue.findByParentid", query = "SELECT c FROM ClassifierValue c WHERE c.parentid = :parentid")})
public class ClassifierValue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "VALUE_", length = 1000)
    private String value;
    @Column(name = "PARENTID")
    private Integer parentid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifierValue")
    private Collection<FactClassifvalue> factClassifvalueCollection;
    @JoinColumn(name = "CLASSIFID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Classifier classifier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifierValue")
    private Collection<CollfactClassifvalue> collfactClassifvalueCollection;

    public ClassifierValue() {
    }

    public ClassifierValue(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Collection<FactClassifvalue> getFactClassifvalueCollection() {
        return factClassifvalueCollection;
    }

    public void setFactClassifvalueCollection(Collection<FactClassifvalue> factClassifvalueCollection) {
        this.factClassifvalueCollection = factClassifvalueCollection;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public Collection<CollfactClassifvalue> getCollfactClassifvalueCollection() {
        return collfactClassifvalueCollection;
    }

    public void setCollfactClassifvalueCollection(Collection<CollfactClassifvalue> collfactClassifvalueCollection) {
        this.collfactClassifvalueCollection = collfactClassifvalueCollection;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.ClassifierValue[id=" + id + "]";
    }

}
