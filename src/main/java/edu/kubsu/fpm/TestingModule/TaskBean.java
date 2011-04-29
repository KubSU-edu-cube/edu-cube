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

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public TaskBean(){

    }
}
