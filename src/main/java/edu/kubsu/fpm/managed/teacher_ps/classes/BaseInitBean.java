package edu.kubsu.fpm.managed.teacher_ps.classes;

import edu.kubsu.fpm.DAO.CountryDAO;
import edu.kubsu.fpm.DAO.DepartmentDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Department;
import edu.kubsu.fpm.entity.Faculty;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.entity.University;
import edu.kubsu.fpm.managed.classes.ImgConverter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 10.05.12
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class BaseInitBean {

    @EJB
    private DepartmentDAO departmentDAO;
    @EJB
    private CountryDAO countryDAO;
    @EJB
    private PersonDAO personDAO;

    public void tempBaseInit() {
        try {
            persistPerson();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        University university = new University();
        university.setCountry("Россия");
        university.setCity("Москва");
        university.setName("МГУ");

        Faculty faculty = new Faculty();
        faculty.setName("Механико-математический");
        faculty.setUniversity(university);

        List<Faculty> faculties = new ArrayList<Faculty>();
        faculties.add(faculty);

        university.setFaculties(faculties);

        Department department = new Department();
        department.setFaculty(faculty);
        department.setName("Дискретной Математики");

        departmentDAO.persist(department);
    }
    private void persistPerson() throws FileNotFoundException {

        savePerson("Доктор физико-математических наук","ул. Восточная 340, кв. 54","Иваново","Москва"
                ,"Россия",InsertDate(21, 05, 1960),"254-93-77","1957203","+7(951)2304978",
                "Елена","Ивановна","женский" ,"orf_541","Молчалина","","temp_img/molchalina_e_i.jpg");

        savePerson("Кандидат наук","ул. Гоголя 30, кв. 7","Москва","Москва"
                ,"Россия",InsertDate(2, 06, 1987),"254-93-77","1957203","+7(951)2304978",
                "Елена","Викторовна","женский" ,"orf_541","Молчалина","","temp_img/molchalina_e_v.jpg");


    }

    private void savePerson(String additionalInfo, String addr, String cityOfBirth, String currentCity, String currentCountry, Date dateOfBirth, String homeTel, String icq, String mobtel, String name, String patronymic, String sex, String skype, String surname, String site, String photo) {
        Person person1 = new Person();
        person1.setAdditionalInformation(additionalInfo);
        person1.setAdress(addr);
        person1.setCityOfBirth(cityOfBirth);
        person1.setCurrentCity(currentCity);
        person1.setCurrentCountry(currentCountry);
        person1.setDateOfBirth(dateOfBirth);
        person1.setHomeTel(homeTel);
        person1.setIcq(icq);
        person1.setMobTel(mobtel);
        person1.setName(name);
        person1.setPatronymic(patronymic);
        person1.setSex(sex);
        person1.setSkype(skype);
        person1.setSurname(surname);
        person1.setWebSite(site);
        person1.setPhoto(getBytesFromFile(photo));

        personDAO.persist(person1);
    }
    private byte[] getBytesFromFile(String fName) {

        try {
            File imgFile = new File(fName);
            Image image = ImageIO.read(new FileInputStream(fName));
            byte[] resisedImg = ImgConverter.changeProportion(imgFile, null, image.getWidth(null), image.getHeight(null), 100, 150);
            return resisedImg;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
    private Date InsertDate(int day, int month, int year) {
        Date d = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        d = calendar.getTime();
        return d;
    }
}
