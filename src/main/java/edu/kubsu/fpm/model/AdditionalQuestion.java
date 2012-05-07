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
@Table(name = "ADDITIONAL_QUESTION")
@NamedQueries({
    @NamedQuery(name = "AdditionalQuestion.findAll", query = "SELECT a FROM AdditionalQuestion a"),
    @NamedQuery(name = "AdditionalQuestion.findById", query = "SELECT a FROM AdditionalQuestion a WHERE a.id = :id")})
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
    private Group group;

    public AdditionalQuestion() {
    }

    public AdditionalQuestion(Integer percentObligatoryQuestion, int percentRigthAnswers, Integer percentAdditionalQuestion, Group group) {
        this.percentObligatoryQuestion = percentObligatoryQuestion;
        this.percentAdditionalQuestion = percentAdditionalQuestion;
        this.percentRigthAnswers = percentRigthAnswers;
        this.group = group;
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

    public Group getGroupid() {
        return group;
    }

    public void setGroupid(Group groupid) {
        this.group = groupid;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.AdditionalQuestion[id=" + id + "]";
    }

}
