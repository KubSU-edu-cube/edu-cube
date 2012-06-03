package edu.kubsu.fpm.ejb;

import edu.kubsu.fpm.managed.classes.media_classes.Video;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 30.05.12
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@Local(DBVideoLocal.class)
public class DBVideoBean implements DBVideoLocal{
    private List<Video> videoList = new ArrayList<Video>();

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }
}
