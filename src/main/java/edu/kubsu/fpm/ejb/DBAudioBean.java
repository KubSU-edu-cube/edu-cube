package edu.kubsu.fpm.ejb;

import edu.kubsu.fpm.managed.classes.media_classes.Audio;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 17.05.12
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@Local(DBAudioLocal.class)
public class DBAudioBean implements DBAudioLocal{
    private List<Audio> audioList = new ArrayList<Audio>();

    public List<Audio> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<Audio> audioList) {
        this.audioList = audioList;
    }
}
