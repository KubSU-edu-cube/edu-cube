/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Марина
 */
@Entity
@Table(name = "FACT_CLASSIFVALUE", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "FactClassifvalue.findAll", query = "SELECT f FROM FactClassifvalue f"),
    @NamedQuery(name = "FactClassifvalue.findByFactid", query = "SELECT f FROM FactClassifvalue f WHERE f.factClassifvaluePK.factid = :factid"),
    @NamedQuery(name = "FactClassifvalue.findByClassifid", query = "SELECT f FROM FactClassifvalue f WHERE f.factClassifvaluePK.classifid = :classifid"),
    @NamedQuery(name = "FactClassifvalue.findByClassifValueid", query = "SELECT f FROM FactClassifvalue f WHERE f.factClassifvaluePK.classifValueid = :classifValueid")})
public class FactClassifvalue implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FactClassifvaluePK factClassifvaluePK;
    @JoinColumn(name = "FACTID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FactCollection factCollection;
    @JoinColumn(name = "CLASSIF_VALUEID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ClassifierValue classifierValue;
    @JoinColumn(name = "CLASSIFID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classifier classifier;

    public FactClassifvalue() {
    }

    public FactClassifvalue(FactClassifvaluePK factClassifvaluePK) {
        this.factClassifvaluePK = factClassifvaluePK;
    }

    public FactClassifvalue(int factid, int classifid, int classifValueid) {
        this.factClassifvaluePK = new FactClassifvaluePK(factid, classifid, classifValueid);
    }

    public FactClassifvaluePK getFactClassifvaluePK() {
        return factClassifvaluePK;
    }

    public void setFactClassifvaluePK(FactClassifvaluePK factClassifvaluePK) {
        this.factClassifvaluePK = factClassifvaluePK;
    }

    public FactCollection getFactCollection() {
        return factCollection;
    }

    public void setFactCollection(FactCollection factCollection) {
        this.factCollection = factCollection;
    }

    public ClassifierValue getClassifierValue() {
        return classifierValue;
    }

    public void setClassifierValue(ClassifierValue classifierValue) {
        this.classifierValue = classifierValue;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factClassifvaluePK != null ? factClassifvaluePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactClassifvalue)) {
            return false;
        }
        FactClassifvalue other = (FactClassifvalue) object;
        if ((this.factClassifvaluePK == null && other.factClassifvaluePK != null) || (this.factClassifvaluePK != null && !this.factClassifvaluePK.equals(other.factClassifvaluePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.FactClassifvalue[factClassifvaluePK=" + factClassifvaluePK + "]";
    }

}
