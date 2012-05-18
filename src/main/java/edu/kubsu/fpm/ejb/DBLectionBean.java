package edu.kubsu.fpm.ejb;

import edu.kubsu.fpm.managed.teacher_ps.classes.CourseLection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 27.04.12
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@Local(DBLectionLocal.class)
public class DBLectionBean implements DBLectionLocal {
    private List<CourseLection> lections = new ArrayList<CourseLection>();

    @Override
    public List<CourseLection> getLections() {
        return this.lections;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLections(List<CourseLection> list) {
        this.lections = list;
    }
}
