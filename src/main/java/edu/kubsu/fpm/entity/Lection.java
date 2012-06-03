package edu.kubsu.fpm.entity;

import edu.kubsu.fpm.model.ClassifierValue;
import edu.kubsu.fpm.model.Mark;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 26.04.12
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Lection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="prev_ver_id", referencedColumnName="id")
    private Lection lection;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="LECTION_COURSE",
            joinColumns=
            @JoinColumn(name="LECTION_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="COURSE_ID", referencedColumnName="ID")
    )
    private List<Course> courses;

    @Column(length = 2000)
    private String description;

    @Lob
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="LECTION_COURSE_VARIATION",
            joinColumns=
            @JoinColumn(name="LECTION_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="COURSE_VARIATION_ID", referencedColumnName="ID")
    )

    private List<Course_variation> variationList = new ArrayList<>();

    @OneToOne(mappedBy = "lection", cascade = CascadeType.ALL)
    private ClassifierValue classifierValue;
    
    @OneToMany(mappedBy = "lection", cascade = CascadeType.ALL)
    private List<Test> testList;
    
    @OneToMany(mappedBy = "lection", cascade = CascadeType.ALL)
    private List<Mark> markList;

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }

    public ClassifierValue getClassifierValue() {
        return classifierValue;
    }

    public void setClassifierValue(ClassifierValue classifierValue) {
        this.classifierValue = classifierValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lection getLection() {
        return lection;
    }

    public void setLection(Lection lection) {
        this.lection = lection;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public List<Course_variation> getVariationList() {
        return variationList;
    }

    public void setVariationList(List<Course_variation> variationList) {
        this.variationList = variationList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
