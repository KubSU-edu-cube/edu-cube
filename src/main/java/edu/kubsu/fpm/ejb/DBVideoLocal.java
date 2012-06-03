package edu.kubsu.fpm.ejb;

import edu.kubsu.fpm.managed.classes.media_classes.Video;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 30.05.12
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface DBVideoLocal {
    public List<Video> getVideoList();

    public void setVideoList(List<Video> audioList);
}
