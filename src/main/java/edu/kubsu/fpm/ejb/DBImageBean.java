package edu.kubsu.fpm.ejb;

import edu.kubsu.fpm.managed.classes.media_classes.Image;
import edu.kubsu.fpm.managed.teacher_ps.classes.PersonalPhoto;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 26.04.11
 * Time: 11:44
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@Local(DBImageLocal.class)
public class DBImageBean implements DBImageLocal {
    private List<Image> imgList = new ArrayList<Image>();
    private List<PersonalPhoto> smallImgs = new ArrayList<PersonalPhoto>();
    private PersonalPhoto mainPhoto = new PersonalPhoto(null);

    public List<Image> getImgList() {
        return imgList;
    }

    public void setImgList(List<Image> imgList) {
        this.imgList = imgList;
    }

    public List<PersonalPhoto> getSmallImgs() {
        return smallImgs;
    }

    public void setSmallImgs(List<PersonalPhoto> smallImgs) {
        this.smallImgs = smallImgs;
    }

    public PersonalPhoto getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(PersonalPhoto mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
}
