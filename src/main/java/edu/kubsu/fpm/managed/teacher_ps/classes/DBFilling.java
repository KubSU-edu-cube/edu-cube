package edu.kubsu.fpm.managed.teacher_ps.classes;

import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Person;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 16.04.12
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class DBFilling {
    @EJB
    private PersonDAO personDAO;

    public DBFilling() {
        

    }

    public void persisPerson() {
        Person person1 = new Person();
        person1.setAdditionalInformation("Доктор наук");
        person1.setAdress("ул. Рашпилевская 50, кв. 14");
        person1.setCityOfBirth("Ижевск");
        person1.setCurrentCity("Краснодар");
        person1.setCurrentCountry("Россия");
        person1.setDateOfBirth(InsertDate(21, 05, 1951));
        person1.setHomeTel("254-93-77");
        person1.setIcq("1957203");
        person1.setMobTel("+7(951)2304978");
        person1.setName("Ирина");
        person1.setPatronymic("Викторовна");
        person1.setSex("женский");
        person1.setSkype("orf_541");
        person1.setSurname("Семенова");
        person1.setWebSite("");

        personDAO.persist(person1);

        Person person2 = new Person();
        person2.setAdditionalInformation("");
        person2.setAdress("ул Севастопольская 8");
        person2.setCityOfBirth("Краснодар");
        person2.setCurrentCity("Краснодар");
        person2.setCurrentCountry("Россия");
        person2.setDateOfBirth(InsertDate(11, 05, 1963));
        person2.setHomeTel("");
        person2.setIcq("");
        person2.setMobTel("+7(918)2333367");
        person2.setName("Александр");
        person2.setPatronymic("Петрович");
        person2.setSex("мужской");
        person2.setSkype("");
        person2.setSurname("Дроботько");
        person2.setWebSite("");

        personDAO.persist(person2);

        Person person3 = new Person();
        person3.setAdditionalInformation("студент");
        person3.setAdress("");
        person3.setCityOfBirth("");
        person3.setCurrentCity("");
        person3.setCurrentCountry("");
        person3.setDateOfBirth(InsertDate(20, 8, 1951));
        person3.setHomeTel("");
        person3.setIcq("");
        person3.setMobTel("");
        person3.setName("Константин");
        person3.setPatronymic("");
        person3.setSex("");
        person3.setSkype("");
        person3.setSurname("Васильев");
        person3.setWebSite("");

        personDAO.persist(person3);
       
        
    }

    private Date InsertDate(int day, int month, int year) {
        Date d = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        d = calendar.getTime();
        return d;
    }
}
