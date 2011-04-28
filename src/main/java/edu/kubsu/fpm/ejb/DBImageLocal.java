package edu.kubsu.fpm.ejb;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 26.04.11
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface DBImageLocal {
    /**
     *  Возвращает лист картинок
     */
    public List<byte[]> getImgList();
    public  void setImgList(List<byte[]> list);
}
