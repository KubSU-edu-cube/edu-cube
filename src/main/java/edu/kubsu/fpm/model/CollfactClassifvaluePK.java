/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package edu.kubsu.fpm.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
*
* @author Марина
*/
@Embeddable
public class CollfactClassifvaluePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "COLLID", nullable = false)
    private int collid;
    @Basic(optional = false)
    @Column(name = "CLASSIFID", nullable = false)
    private int classifid;
    @Basic(optional = false)
    @Column(name = "CLASSIF_VALUEID", nullable = false)
    private int classifValueid;

    public CollfactClassifvaluePK() {
    }

    public CollfactClassifvaluePK(int collid, int classifid, int classifValueid) {
        this.collid = collid;
        this.classifid = classifid;
        this.classifValueid = classifValueid;
    }

    public int getCollid() {
        return collid;
    }

    public void setCollid(int collid) {
        this.collid = collid;
    }

    public int getClassifid() {
        return classifid;
    }

    public void setClassifid(int classifid) {
        this.classifid = classifid;
    }

    public int getClassifValueid() {
        return classifValueid;
    }

    public void setClassifValueid(int classifValueid) {
        this.classifValueid = classifValueid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) collid;
        hash += (int) classifid;
        hash += (int) classifValueid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollfactClassifvaluePK)) {
            return false;
        }
        CollfactClassifvaluePK other = (CollfactClassifvaluePK) object;
        if (this.collid != other.collid) {
            return false;
        }
        if (this.classifid != other.classifid) {
            return false;
        }
        if (this.classifValueid != other.classifValueid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.CollfactClassifvaluePK[collid=" + collid + ", classifid=" + classifid + ", classifValueid=" + classifValueid + "]";
    }

}
