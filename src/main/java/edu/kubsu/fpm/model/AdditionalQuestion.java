/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Марина
 */
@Entity
@Table(name = "ADITIONAL_QUESTION")
@NamedQueries({
    @NamedQuery(name = "AditionalQuestion.findAll", query = "SELECT a FROM AdditionalQuestion a"),
    @NamedQuery(name = "AditionalQuestion.findById", query = "SELECT a FROM AdditionalQuestion a WHERE a.id = :id")})
public class AdditionalQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "PERCENT_OBLIGATORY_QUESTION", nullable = false)
    private Integer percentObligatoryQuestion;
    @Column(name = "PERCENT_ADDITIONAL_QUESTION", nullable = false)
    private Integer percentAdditionalQuestion;
    @Column(name = "PERCENT_RIGHT_ANSWERS", nullable = false)
    private int percentRigthAnswers;
    @JoinColumn(name = "GROUPID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Groups groupid;
    @JoinColumn(name = "CLASSIF_VALUESID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ClassifierValue classifValuesid;

    public AdditionalQuestion() {
    }

    public AdditionalQuestion(Integer id) {
        this.id = id;
    }

    public AdditionalQuestion(Integer id, int percentObligatoryQuestion, int percentAdditionalQuestion, int percentRigthAnswers) {
        this.id = id;
        this.percentObligatoryQuestion = percentObligatoryQuestion;
        this.percentAdditionalQuestion = percentAdditionalQuestion;
        this.percentRigthAnswers = percentRigthAnswers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPercentObligatoryQuestion() {
        return percentObligatoryQuestion;
    }

    public void setPercentObligatoryQuestion(Integer percentObligatoryQuestion) {
        this.percentObligatoryQuestion = percentObligatoryQuestion;
    }

    public Integer getPercentAdditionalQuestion() {
        return percentAdditionalQuestion;
    }

    public void setPercentAdditionalQuestion(Integer percentAdditionalQuestion) {
        this.percentAdditionalQuestion = percentAdditionalQuestion;
    }

    public int getPercentRigthAnswers() {
        return percentRigthAnswers;
    }

    public void setPercentRigthAnswers(int percentRigthAnswers) {
        this.percentRigthAnswers = percentRigthAnswers;
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
        if (!(object instanceof AdditionalQuestion)) {
            return false;
        }
        AdditionalQuestion other = (AdditionalQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.AdditionalQuestion[id=" + id + "]";
    }

}
