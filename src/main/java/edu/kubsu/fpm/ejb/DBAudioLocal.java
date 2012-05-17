package edu.kubsu.fpm.ejb;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 17.05.12
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface DBAudioLocal {
    public List<byte[]> getAudioList();

    public void setAudioList(List<byte[]> audioList);
}
