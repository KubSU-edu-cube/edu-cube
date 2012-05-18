package edu.kubsu.fpm.ejb;

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
    private List<byte[]> audioList = new ArrayList<byte[]>();

    public List<byte[]> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<byte[]> audioList) {
        this.audioList = audioList;
    }
}
