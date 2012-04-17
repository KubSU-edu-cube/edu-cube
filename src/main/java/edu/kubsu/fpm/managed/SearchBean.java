package edu.kubsu.fpm.managed;

import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.ejb.DBImageLocal;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.managed.teacher_ps.classes.ShortPersonInfo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 16.04.12
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class SearchBean {
    
    private String simpleQuery;
    private List<ShortPersonInfo> shortPersonInfos;

    @EJB
    private PersonDAO personDAO;
    @EJB
    private DBImageLocal imageLocal;


    public void testShowPerson(){
       List<Person> personList = personDAO.findBy2Names("Ирина","Семенова");
       List<byte[]> smalImgs = new ArrayList<byte[]>();

       Person person = personList.get(0);

       shortPersonInfos = new ArrayList<ShortPersonInfo>();
       ShortPersonInfo personInfo = new ShortPersonInfo();
       personInfo.setName(person.getName());
       personInfo.setSurmane(person.getSurname());
       personInfo.setCity(person.getCurrentCity());
       personInfo.setAge(getPersonAge(person.getDateOfBirth()));

        byte[] smalImg = person.getPhoto();
        smalImgs.add(smalImg);
        imageLocal.setSmallImgs(smalImgs);




    }

    private int getPersonAge(Date dateOfBirth) {
        return 0;  //To change body of created methods use File | Settings | File Templates.
    }
}

