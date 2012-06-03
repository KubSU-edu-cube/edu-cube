package edu.kubsu.fpm.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 29.05.12
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class MediaContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private Serializable content;

    @Column(name = "CONTENT_NAME")
    private String contentName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FACT_ID", referencedColumnName = "ID")
    private Fact fact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Serializable getContent() {
        return content;
    }

    public void setContent(Serializable content) {
        this.content = content;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }
}
