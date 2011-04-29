package edu.kubsu.fpm.TestingModule;

import com.sun.jmx.snmp.tasks.Task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by IntelliJ IDEA.
 * User: Марина
 * Date: 29.04.11
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "taskBean")
@SessionScoped
public class TaskBean {

    private String testResult = "";
    private Integer rightAnswer = 0;
    private Integer countAnswer = 0;

    public String getTestResult() {
        testResult = "Вы ответили на ".concat(this.getRightAnswer().toString()).concat(" из ").concat(this.getCountAnswer().toString());
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public Integer getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(Integer countAnswer) {
        this.countAnswer = countAnswer;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public TaskBean(){

    }
}
