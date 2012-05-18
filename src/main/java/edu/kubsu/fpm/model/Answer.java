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
@Table(name = "ANSWER")
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findById", query = "SELECT a FROM Answer a WHERE a.id = :id"),
    @NamedQuery(name = "Answer.findByPathAnswFile", query = "SELECT a FROM Answer a WHERE a.pathAnswFile = :pathAnswFile")})
public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PATH_ANSW_FILE", nullable = false, length = 500)
    private String pathAnswFile;
    @Basic(optional = false)
    @Lob
    @Column(name = "TEXT_QUESTION", nullable = false)
    private Serializable textQuestion;
    @JoinColumn(name = "STUDENTID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Student studentid;
    @JoinColumn(name = "CLASSIF_VALUESID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ClassifierValue classifValuesid;

    public Answer() {
    }

    public Answer(Integer id) {
        this.id = id;
    }

    public Answer(Integer id, String pathAnswFile, Serializable textQuestion) {
        this.id = id;
        this.pathAnswFile = pathAnswFile;
        this.textQuestion = textQuestion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathAnswFile() {
        return pathAnswFile;
    }

    public void setPathAnswFile(String pathAnswFile) {
        this.pathAnswFile = pathAnswFile;
    }

    public Serializable getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(Serializable textQuestion) {
        this.textQuestion = textQuestion;
    }

    public Student getStudentid() {
        return studentid;
    }

    public void setStudentid(Student studentid) {
        this.studentid = studentid;
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
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.Answer[id=" + id + "]";
    }

}
