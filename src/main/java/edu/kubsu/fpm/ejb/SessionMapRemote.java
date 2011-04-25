package edu.kubsu.fpm.ejb;

import javax.ejb.Remote;

/**
 * Created by IntelliJ IDEA.
 * User: acer
 * Date: 26.04.11
 * Time: 0:15
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface SessionMapRemote {
    public String getName();
}
