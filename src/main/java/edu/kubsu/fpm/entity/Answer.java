package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 10:49
 */

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    private Boolean isRight;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<AllAnswers> answersList;

    public Answer() {
    }

    public Answer(String content, Boolean right) {
        this.content = content;
        isRight = right;
    }

    public List<AllAnswers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<AllAnswers> answersList) {
        this.answersList = answersList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }
}
