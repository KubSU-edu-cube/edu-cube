package edu.kubsu.fpm.ejb;

import edu.kubsu.fpm.managed.teacher_ps.classes.CourseLection;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 27.04.12
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface DBLectionLocal {

    public List<CourseLection> getLections();
    public  void setLections(List<CourseLection> list);
}
