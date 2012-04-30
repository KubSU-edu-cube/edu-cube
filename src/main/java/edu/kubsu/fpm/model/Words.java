/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Marina
 */
@Entity
@Table(name = "WORDS")
@NamedQueries({
    @NamedQuery(name = "Words.findAll", query = "SELECT w FROM Words w"),
    @NamedQuery(name = "Words.findById", query = "SELECT w FROM Words w WHERE w.id = :id"),
    @NamedQuery(name = "Words.findByWord", query = "SELECT w FROM Words w WHERE w.word = :word")})
public class Words implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "WORD")
    private String word;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "words")
    private List<SynAnt> synAntList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wordsDepend")
    private List<SynAnt> synAntCollection1;

    public Words() {
    }

    public Words(Integer id) {
        this.id = id;
    }

    public Words(Integer id, String word) {
        this.id = id;
        this.word = word;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<SynAnt> getSynAntList() {
        return synAntList;
    }

    public void setSynAntList(List<SynAnt> synAntCollection) {
        this.synAntList = synAntCollection;
    }

    public Collection<SynAnt> getSynAntCollection1() {
        return synAntCollection1;
    }

    public void setSynAntCollection1(List<SynAnt> synAntCollection1) {
        this.synAntCollection1 = synAntCollection1;
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
        if (!(object instanceof Words)) {
            return false;
        }
        Words other = (Words) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "edu.student.richfaces.Words[ id=" + id + " ]";
    }
    
}
