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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Марина
 */
@Entity
@Table(name = "FACT_COLLECTION")
@NamedQueries({
    @NamedQuery(name = "FactCollection.findAll", query = "SELECT f FROM FactCollection f"),
    @NamedQuery(name = "FactCollection.findById", query = "SELECT f FROM FactCollection f WHERE f.id = :id"),
    @NamedQuery(name = "FactCollection.findByFactcollName", query = "SELECT f FROM FactCollection f WHERE f.factcollName = :factcollName")})
public class FactCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "FACTCOLL_NAME", length = 300)
    private String factcollName;
    @JoinTable(name = "COLL_DEPEND_FROM", joinColumns = {
        @JoinColumn(name = "DEPENDENT_COLLID", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "COLLID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private Collection<FactCollection> factCollectionCollection;
    @ManyToMany(mappedBy = "factCollectionCollection")
    private Collection<FactCollection> factCollectionCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factCollection")
    private Collection<FactClassifvalue> factClassifvalueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factCollection")
    private Collection<CollfactClassifvalue> collfactClassifvalueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "collid")
    private Collection<Fact> factCollection;

    public FactCollection() {
    }

    public FactCollection(Integer id) {
        this.id = id;
    }

    public FactCollection(String newfactcollName) {
        this.factcollName = newfactcollName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactcollName() {
        return factcollName;
    }

    public void setFactcollName(String factcollName) {
        this.factcollName = factcollName;
    }

    public Collection<FactCollection> getFactCollectionCollection() {
        return factCollectionCollection;
    }

    public void setFactCollectionCollection(Collection<FactCollection> factCollectionCollection) {
        this.factCollectionCollection = factCollectionCollection;
    }

    public Collection<FactCollection> getFactCollectionCollection1() {
        return factCollectionCollection1;
    }

    public void setFactCollectionCollection1(Collection<FactCollection> factCollectionCollection1) {
        this.factCollectionCollection1 = factCollectionCollection1;
    }

    public Collection<FactClassifvalue> getFactClassifvalueCollection() {
        return factClassifvalueCollection;
    }

    public void setFactClassifvalueCollection(Collection<FactClassifvalue> factClassifvalueCollection) {
        this.factClassifvalueCollection = factClassifvalueCollection;
    }

    public Collection<CollfactClassifvalue> getCollfactClassifvalueCollection() {
        return collfactClassifvalueCollection;
    }

    public void setCollfactClassifvalueCollection(Collection<CollfactClassifvalue> collfactClassifvalueCollection) {
        this.collfactClassifvalueCollection = collfactClassifvalueCollection;
    }

    public Collection<Fact> getFactCollection() {
        return factCollection;
    }

    public void setFactCollection(Collection<Fact> factCollection) {
        this.factCollection = factCollection;
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
        if (!(object instanceof FactCollection)) {
            return false;
        }
        FactCollection other = (FactCollection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.FactCollection[id=" + id + "]";
    }

}
