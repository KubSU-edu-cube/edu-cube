package edu.kubsu.fpm.ejb;

import edu.kubsu.fpm.managed.classes.media_classes.Image;
import edu.kubsu.fpm.managed.teacher_ps.classes.PersonalPhoto;

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
    public List<Image> getImgList();
    public  void setImgList(List<Image> list);
    public List<PersonalPhoto> getSmallImgs();
    public  void setSmallImgs(List<PersonalPhoto> list);
    public PersonalPhoto getMainPhoto();
    public void setMainPhoto(PersonalPhoto personalPhoto);
}
