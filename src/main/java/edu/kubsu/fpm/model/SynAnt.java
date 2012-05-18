///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package edu.kubsu.fpm.model;
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//
///**
// *
// * @author Марина
// */
//@Entity
//@Table(name = "SYN_ANT")
//@NamedQueries({
//    @NamedQuery(name = "SynAnt.findAll", query = "SELECT s FROM SynAnt s"),
//    @NamedQuery(name = "SynAnt.findById", query = "SELECT s FROM SynAnt s WHERE s.id = :id"),
//    @NamedQuery(name = "SynAnt.findByTypeWord", query = "SELECT s FROM SynAnt s WHERE s.typeWord = :typeWord"),
//    @NamedQuery(name = "SynAnt.findByWord", query = "SELECT s FROM SynAnt s WHERE s.word = :word")})
//public class SynAnt implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "ID", nullable = false)
//    private Integer id;
//    @Basic(optional = false)
//    @Column(name = "TYPE_WORD", nullable = false, length = 200)
//    private String typeWord;
//    @Basic(optional = false)
//    @Column(name = "WORD", nullable = false, length = 200)
//    private String word;
//
//    public SynAnt() {
//    }
//
//    public SynAnt(Integer id) {
//        this.id = id;
//    }
//
//    public SynAnt(Integer id, String typeWord, String word) {
//        this.id = id;
//        this.typeWord = typeWord;
//        this.word = word;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTypeWord() {
//        return typeWord;
//    }
//
//    public void setTypeWord(String typeWord) {
//        this.typeWord = typeWord;
//    }
//
//    public String getWord() {
//        return word;
//    }
//
//    public void setWord(String word) {
//        this.word = word;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof SynAnt)) {
//            return false;
//        }
//        SynAnt other = (SynAnt) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "edu.kubsu.fpm.model.SynAnt[id=" + id + "]";
//    }
//
//}
