/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marina
 */
@Entity
@Table(name = "SYN_ANT")
@NamedQueries({
    @NamedQuery(name = "SynAnt.findAll", query = "SELECT s FROM SynAnt s"),
    @NamedQuery(name = "SynAnt.findById", query = "SELECT s FROM SynAnt s WHERE s.synAntPK.id = :id"),
    @NamedQuery(name = "SynAnt.findByIddepend", query = "SELECT s FROM SynAnt s WHERE s.synAntPK.iddepend = :iddepend"),
    @NamedQuery(name = "SynAnt.findByRelation", query = "SELECT s FROM SynAnt s WHERE s.relation = :relation")})
public class SynAnt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SynAntPK synAntPK;
    @Column(name = "RELATION")
    private String relation;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Words words;
    @JoinColumn(name = "IDDEPEND", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Words words1;

    public SynAnt() {
    }

    public SynAnt(SynAntPK synAntPK) {
        this.synAntPK = synAntPK;
    }

    public SynAnt(SynAntPK synAntPK, String relation) {
        this.synAntPK = synAntPK;
        this.relation = relation;
    }

    public SynAnt(int id, int iddepend) {
        this.synAntPK = new SynAntPK(id, iddepend);
    }

    public SynAntPK getSynAntPK() {
        return synAntPK;
    }

    public void setSynAntPK(SynAntPK synAntPK) {
        this.synAntPK = synAntPK;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public Words getWords1() {
        return words1;
    }

    public void setWords1(Words words1) {
        this.words1 = words1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (synAntPK != null ? synAntPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SynAnt)) {
            return false;
        }
        SynAnt other = (SynAnt) object;
        return !((this.synAntPK == null && other.synAntPK != null) || (this.synAntPK != null && !this.synAntPK.equals(other.synAntPK)));
    }

    @Override
    public String toString() {
        return "edu.student.richfaces.SynAnt[ synAntPK=" + synAntPK + " ]";
    }
    
}
