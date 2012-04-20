/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifValuesid")
    private Collection<Mark> markCollection;
    @JoinColumn(name = "CLASSIFID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Classifier classifier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifValuesid")
    private Collection<Groups> groupsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifValuesid")
    private Collection<AditionalQuestion> aditionalQuestionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifierValue")
    private Collection<CollfactClassifvalue> collfactClassifvalueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classifValuesid")
    private Collection<Answer> answerCollection;

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

    public Collection<Mark> getMarkCollection() {
        return markCollection;
    }

    public void setMarkCollection(Collection<Mark> markCollection) {
        this.markCollection = markCollection;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }

    public Collection<AditionalQuestion> getAditionalQuestionCollection() {
        return aditionalQuestionCollection;
    }

    public void setAditionalQuestionCollection(Collection<AditionalQuestion> aditionalQuestionCollection) {
        this.aditionalQuestionCollection = aditionalQuestionCollection;
    }

    public Collection<CollfactClassifvalue> getCollfactClassifvalueCollection() {
        return collfactClassifvalueCollection;
    }

    public void setCollfactClassifvalueCollection(Collection<CollfactClassifvalue> collfactClassifvalueCollection) {
        this.collfactClassifvalueCollection = collfactClassifvalueCollection;
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
        if (!(object instanceof ClassifierValue)) {
            return false;
        }
        ClassifierValue other = (ClassifierValue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.ClassifierValue[id=" + id + "]";
    }

}
