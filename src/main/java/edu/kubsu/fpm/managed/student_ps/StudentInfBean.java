package edu.kubsu.fpm.managed.student_ps;

import edu.kubsu.fpm.DAO.DepartmentDAO;
import edu.kubsu.fpm.DAO.EducationDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.Department;
import edu.kubsu.fpm.entity.Faculty;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.entity.University;
import edu.kubsu.fpm.managed.classes.ImgConverter;
import edu.kubsu.fpm.entity.Education;
import edu.kubsu.fpm.entity.Education_status;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: Marina
 * Date: 08.05.12
 * Time: 11:28
 */

@ManagedBean
@SessionScoped
public class StudentInfBean {

    private String studentId;       // идентификатор владельца страницы
    private String name = "Анна";
    private String patronymic = "Ивановна";
    private String surname = "Петрова";
    private Date dateOfBirth;
    private String sex = "женский";
    private String cityOfBirth = "Краснодар";
    private String currentCountry = "Россия";
    private String currentCity = "Краснодар";
    private String adress = "ул. Солнечная, д. 5, кв. 13";
    private String mobTel = "8-918-322-23-22";
    private String homeTel = "2145677";
    private String skype = "aipetr";
    private String icq = "22255339";
    private String webSite = "vk.com";
    private String email = "aipetr@mail.ru";
    private String additionalInformation = "Хорошая ученица.";
    private List<Education> educations;

    @EJB
    private PersonDAO personDAO;

    @EJB
    private DepartmentDAO departmentDAO;

    @EJB
    private EducationDAO educationDAO;
    
    public void initPerson(){
        // Профиль
        Person person = savePerson(additionalInformation, adress, cityOfBirth, currentCity, currentCountry, InsertDate(15, 03, 1990),
                homeTel, icq, mobTel, name, patronymic, sex, skype, surname, webSite, "temp_facts/student_photo.hpg");
        
        //  ВУЗ
        University university = new University();
        university.setCountry("Россия");
        university.setCity("Краснодар");
        university.setName("КубГУ");

        Faculty faculty = new Faculty();
        faculty.setName("ФКТиПМ");
        faculty.setUniversity(university);

        List<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);

        university.setFaculties(faculties);

        Department department = new Department();
        department.setFaculty(faculty);
        department.setName("Информационных Технологий");

        departmentDAO.persist(department);
        
        //
        Education_status education_status = new Education_status();
        education_status.setName("студентка");

        educations = new ArrayList<>();
        Education e = new Education();
        Date sDate = new Date();
        Date eDate = new Date();
        e.setPerson(person);
        e.setDepartment(department);
        e.setEnterDate(sDate);
        e.setEducationStatus(education_status);
        e.setGraduateDate(eDate);
        educationDAO.persist(e);
        educations.add(e);
    }

    private Person savePerson(String additionalInfo, String addr, String cityOfBirth, String currentCity, String currentCountry,
                            Date dateOfBirth, String homeTel, String icq, String mobtel, String name, String patronymic, String sex,
                            String skype, String surname, String site, String photo) {
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
        return person1;
    }

    private byte[] getBytesFromFile(String fName) {

        try {
            File imgFile = new File(fName);
            Image image = ImageIO.read(new FileInputStream(fName));
            byte[] resisedImg = ImgConverter.changeProportion(imgFile, null, image.getWidth(null), image.getHeight(null), 100, 150);
            return resisedImg;
        } catch (IOException e) {
            e.printStackTrace();
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobTel() {
        return mobTel;
    }

    public void setMobTel(String mobTel) {
        this.mobTel = mobTel;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

}
