/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.model;

import javax.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Марина
 */
@Entity
@Table(name = "FACT")
public class Fact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CONTENT_TYPE", nullable = false, length = 300)
    private String contentType;
    @Lob
    @Column(name = "CONTENT", nullable = false)
    private Serializable content;
    @Column(name = "DIFFICULTIE", length = 30)
    private String difficultie;
    @Column(name = "OBLIGATORY", nullable = false)
    private int obligatory;
    @JoinColumn(name = "COLLID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private FactCollection collection;

    @OneToMany(mappedBy = "fact", cascade = CascadeType.ALL)
    private List<MediaContent> mediaContentList;

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

    public FactCollection getCollection() {
        return collection;
    }

    public void setCollection(FactCollection collection) {
        this.collection = collection;
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "edu.kubsu.fpm.model.Fact[id=" + id + "]";
    }

    public List<MediaContent> getMediaContentList() {
        return mediaContentList;
    }

    public void setMediaContentList(List<MediaContent> mediaContentList) {
        this.mediaContentList = mediaContentList;
    }
}
