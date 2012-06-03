/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import edu.kubsu.fpm.entity.Lection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Марина
 */
@Entity
@Table(name = "CLASSIFIER_VALUE")
public class ClassifierValue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @Column(name = "VALUE", length = 1000)
    private String value;

    @Column(name = "PARENTID")
    private Integer parentid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CLASSIFVALUE_LECTION",
            joinColumns =
                    @JoinColumn(name = "CLASSIFVALUE_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
                    @JoinColumn(name = "LECTION_ID", referencedColumnName = "ID")
    )
    private Lection lection;

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

    public Lection getLection() {
        return lection;
    }

    public void setLection(Lection lection) {
        this.lection = lection;
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

    public void setClassifier(Classifier classifid) {
        this.classifier = classifid;
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
