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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Марина
 */
@Entity
@Table(name = "ADITIONAL_QUESTION", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "AditionalQuestion.findAll", query = "SELECT a FROM AditionalQuestion a"),
    @NamedQuery(name = "AditionalQuestion.findById", query = "SELECT a FROM AditionalQuestion a WHERE a.id = :id"),
    @NamedQuery(name = "AditionalQuestion.findByQuestAmount", query = "SELECT a FROM AditionalQuestion a WHERE a.questAmount = :questAmount")})
public class AditionalQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "QUEST_AMOUNT", nullable = false)
    private int questAmount;
    @JoinColumn(name = "GROUPID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Groups groupid;
    @JoinColumn(name = "CLASSIF_VALUESID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ClassifierValue classifValuesid;

    public AditionalQuestion() {
    }

    public AditionalQuestion(Integer id) {
        this.id = id;
    }

    public AditionalQuestion(Integer id, int questAmount) {
        this.id = id;
        this.questAmount = questAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuestAmount() {
        return questAmount;
    }

    public void setQuestAmount(int questAmount) {
        this.questAmount = questAmount;
    }

    public Groups getGroupid() {
        return groupid;
    }

    public void setGroupid(Groups groupid) {
        this.groupid = groupid;
    }

    public ClassifierValue getClassifValuesid() {
        return classifValuesid;
    }

    public void setClassifValuesid(ClassifierValue classifValuesid) {
        this.classifValuesid = classifValuesid;
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
        if (!(object instanceof AditionalQuestion)) {
            return false;
        }
        AditionalQuestion other = (AditionalQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.AditionalQuestion[id=" + id + "]";
    }

}
