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
@Table(name = "COLLFACT_CLASSIFVALUE")
@NamedQueries({
    @NamedQuery(name = "CollfactClassifvalue.findAll", query = "SELECT c FROM CollfactClassifvalue c"),
    @NamedQuery(name = "CollfactClassifvalue.findByCollid", query = "SELECT c FROM CollfactClassifvalue c WHERE c.collfactClassifvaluePK.collid = :collid"),
    @NamedQuery(name = "CollfactClassifvalue.findByClassifid", query = "SELECT c FROM CollfactClassifvalue c WHERE c.collfactClassifvaluePK.classifid = :classifid"),
    @NamedQuery(name = "CollfactClassifvalue.findByClassifValueid", query = "SELECT c FROM CollfactClassifvalue c WHERE c.collfactClassifvaluePK.classifValueid = :classifValueid")})
public class CollfactClassifvalue implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CollfactClassifvaluePK collfactClassifvaluePK;
    @JoinColumn(name = "COLLID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FactCollection factCollection;
    @JoinColumn(name = "CLASSIF_VALUEID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ClassifierValue classifierValue;
    @JoinColumn(name = "CLASSIFID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classifier classifier;

    public CollfactClassifvalue() {
    }

    public CollfactClassifvalue(CollfactClassifvaluePK collfactClassifvaluePK) {
        this.collfactClassifvaluePK = collfactClassifvaluePK;
    }

    public CollfactClassifvalue(int collid, int classifid, int classifValueid) {
        this.collfactClassifvaluePK = new CollfactClassifvaluePK(collid, classifid, classifValueid);
    }

    public CollfactClassifvaluePK getCollfactClassifvaluePK() {
        return collfactClassifvaluePK;
    }

    public void setCollfactClassifvaluePK(CollfactClassifvaluePK collfactClassifvaluePK) {
        this.collfactClassifvaluePK = collfactClassifvaluePK;
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
        hash += (collfactClassifvaluePK != null ? collfactClassifvaluePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollfactClassifvalue)) {
            return false;
        }
        CollfactClassifvalue other = (CollfactClassifvalue) object;
        if ((this.collfactClassifvaluePK == null && other.collfactClassifvaluePK != null) || (this.collfactClassifvaluePK != null && !this.collfactClassifvaluePK.equals(other.collfactClassifvaluePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.CollfactClassifvalue[collfactClassifvaluePK=" + collfactClassifvaluePK + "]";
    }

}
