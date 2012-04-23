/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author Marina
 */
@Embeddable
public class SynAntPK implements Serializable {
    @Column(name = "ID")
    private int id;
    @Column(name = "IDDEPEND")
    private int iddepend;

    public SynAntPK() {
    }

    public SynAntPK(int id, int iddepend) {
        this.id = id;
        this.iddepend = iddepend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddepend() {
        return iddepend;
    }

    public void setIddepend(int iddepend) {
        this.iddepend = iddepend;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id;
        hash += iddepend;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SynAntPK)) {
            return false;
        }
        SynAntPK other = (SynAntPK) object;
        if (this.id != other.id) {
            return false;
        }
        return this.iddepend == other.iddepend;
    }

    @Override
    public String toString() {
        return "edu.student.richfaces.SynAntPK[ id=" + id + ", iddepend=" + iddepend + " ]";
    }
    
}
