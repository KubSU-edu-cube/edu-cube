package edu.kubsu.fpm.managed;

import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.ejb.DBImageLocal;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.managed.teacher_ps.classes.PersonalPhoto;
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
    private String city;
    private String country;
    private String sex = "Любой";


    @EJB
    private PersonDAO personDAO;
    @EJB
    private DBImageLocal imageLocal;


    public void testShowPerson(){
       List<Person> personList = personDAO.findBy2Names("Ирина","Семенова");
       List<PersonalPhoto> smalImgs = new ArrayList<PersonalPhoto>();

       Person person = personList.get(0);

       shortPersonInfos = new ArrayList<ShortPersonInfo>();
       ShortPersonInfo personInfo = new ShortPersonInfo();
       personInfo.setName(person.getName());
       personInfo.setSurmane(person.getSurname());
       personInfo.setCity(person.getCurrentCity());
       personInfo.setAge(getPersonAge(person.getDateOfBirth()));
       personInfo.setSrc(String.valueOf(person.getId()));

        PersonalPhoto photo = new PersonalPhoto(person.getPhoto(),person.getId());
        smalImgs.add(photo);
        imageLocal.setSmallImgs(smalImgs);




    }

    private int getPersonAge(Date dateOfBirth) {
        return 0;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<ShortPersonInfo> getShortPersonInfos() {
        return shortPersonInfos;
    }

    public void setShortPersonInfos(List<ShortPersonInfo> shortPersonInfos) {
        this.shortPersonInfos = shortPersonInfos;
    }

    public String getSimpleQuery() {
        return simpleQuery;
    }

    public void setSimpleQuery(String simpleQuery) {
        this.simpleQuery = simpleQuery;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

