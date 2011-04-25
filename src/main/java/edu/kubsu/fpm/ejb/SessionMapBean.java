package edu.kubsu.fpm.ejb;

import javax.ejb.Stateless;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey Iskrich
 * Date: 26.04.11
 * Time: 0:07
 */
@Stateless(name = "SessionMapEJB")
public class SessionMapBean implements SessionMapRemote{
    private String name;

    public SessionMapBean() {
        name = "ivanov ivan";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
