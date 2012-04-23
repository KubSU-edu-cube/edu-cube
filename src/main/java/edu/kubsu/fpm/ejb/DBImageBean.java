package edu.kubsu.fpm.ejb;

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
    private List<byte[]> imgList = new ArrayList<byte[]>();
    private List<PersonalPhoto> smallImgs = new ArrayList<PersonalPhoto>();


    public List<byte[]> getImgList() {
        return imgList;
    }

    public void setImgList(List<byte[]> imgList) {
        this.imgList = imgList;
    }

    public List<PersonalPhoto> getSmallImgs() {
        return smallImgs;
    }

    public void setSmallImgs(List<PersonalPhoto> smallImgs) {
        this.smallImgs = smallImgs;
    }
}
