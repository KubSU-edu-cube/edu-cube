/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Марина
 */
@Entity
@Table(name = "FACT", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Fact.findAll", query = "SELECT f FROM Fact f"),
    @NamedQuery(name = "Fact.findById", query = "SELECT f FROM Fact f WHERE f.id = :id"),
    @NamedQuery(name = "Fact.findByContentType", query = "SELECT f FROM Fact f WHERE f.contentType = :contentType"),
    @NamedQuery(name = "Fact.findByDifficultie", query = "SELECT f FROM Fact f WHERE f.difficultie = :difficultie"),
    @NamedQuery(name = "Fact.findByObligatory", query = "SELECT f FROM Fact f WHERE f.obligatory = :obligatory")})
public class Fact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CONTENT_TYPE", nullable = false, length = 300)
    private String contentType;
    @Basic(optional = false)
    @Lob
    @Column(name = "CONTENT", nullable = false)
    private Serializable content;
    @Column(name = "DIFFICULTIE", length = 30)
    private String difficultie;
    @Basic(optional = false)
    @Column(name = "OBLIGATORY", nullable = false)
    private int obligatory;
    @JoinColumn(name = "COLLID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private FactCollection collid;

    public Fact() {
    }

    public Fact(Integer id) {
        this.id = id;
    }

    public Fact(Integer id, String contentType, Serializable content, int obligatory) {
        this.id = id;
        this.contentType = contentType;
        this.content = content;
        this.obligatory = obligatory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Serializable getContent() {
        return content;
    }

    public void setContent(Serializable content) {
        this.content = content;
    }

    public String getDifficultie() {
        return difficultie;
    }

    public void setDifficultie(String difficultie) {
        this.difficultie = difficultie;
    }

    public int getObligatory() {
        return obligatory;
    }

    public void setObligatory(int obligatory) {
        this.obligatory = obligatory;
    }

    public FactCollection getCollid() {
        return collid;
    }

    public void setCollid(FactCollection collid) {
        this.collid = collid;
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
        if (!(object instanceof Fact)) {
            return false;
        }
        Fact other = (Fact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.Fact[id=" + id + "]";
    }

}
