package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.CountryDAO;
import edu.kubsu.fpm.DAO.DepartmentDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.*;
import edu.kubsu.fpm.managed.classes.ImgConverter;
import edu.kubsu.fpm.managed.teacher_ps.classes.DBFilling;
import edu.kubsu.fpm.managed.teacher_ps.classes.Education;
import edu.kubsu.fpm.managed.teacher_ps.classes.Job;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 06.04.12
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@RequestScoped
public class PersonalInfBean {
    private int beanUniqueId = 0;
    
    private String name = "Анна";
    private String surname = "Жуланова";
    private String patronymic = "Павловна";
    private Date dateOfBirth;
    private String sex = "женский";
    private String cityOfBirth = "Краснодар";
    private String currentCountry = "Россия";
    private String currentCity = "Краснодар";
    private String Adress = "ул. Айвазовского 102 А, 29";
    private String mobTel = "+79528616200";
    private String homeTel = "235-32-18";
    private String skype = "julanova-anna";
    private String icq = "78234091";
    private String webSite = "vk.com";
    private String email = "julanovaanna@rambler.ru";
    private String additionalInformation = "дурочка с переулочка";
    private List<Education> educations;
    private List<Job> jobs;

    private Job tmpJob;
    
    private List<City> cities;
    private City selectedCity;
    private List<Country> countries;
    private Country selectedCountry;

    @EJB
    private DepartmentDAO departmentDAO;
    @EJB
    private CountryDAO countryDAO;
    @EJB 
    private PersonDAO personDAO;


//    **************************************************************************************************************
//    ***************************************************************************************************************
    public PersonalInfBean() {



       this.tmpJob = new Job();
        tmpJob.setCountry(null);
        tmpJob.setCity(null);







        dateOfBirth = null;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2012, 9, 1);

        dateOfBirth = calendar.getTime();

        GregorianCalendar curDate = new GregorianCalendar();
        calendar.setTime(new Date());

        GregorianCalendar age = new GregorianCalendar();
        calendar.set(18,0,0);





        
        educations = new ArrayList<Education>();
        Education e = new Education();
        Date sDate = new Date();
        Date eDate = new Date();
        e.setCity("Краснодар");
        e.setCountry("Россия");
        e.setDepartment("Информационных Технологий");
        e.setEnterDate(sDate);
        e.setFaculty("ФКТиПМ");
        e.setGraduateDate(eDate);
        e.setStatus("Студентка");
        e.setUniversity("КубГУ");
        e.setId(beanUniqueId++);
        educations.add(e);
        
        jobs = new ArrayList<Job>();
        Job job = new Job();
        job.setJobId(beanUniqueId++);
        job.setPost("Грузчик");

        jobs.add(job);
        
    }

    public void tempBaseInit() {
        try {
            persistPerson();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        University university = new University();
        university.setCountry("Россия");
        university.setCity("Краснодар");
        university.setName("КубГУ");

        Faculty faculty = new Faculty();
        faculty.setName("ФКТиПМ");
        faculty.setUniversity(university);
        
        List<Faculty> faculties = new ArrayList<Faculty>();
        faculties.add(faculty);

        university.setFaculties(faculties);

        Department department = new Department();
        department.setFaculty(faculty);
        department.setName("Информационных Технологий");

        departmentDAO.persist(department);
    }

    private void persistPerson() throws FileNotFoundException {
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
        person1.setPhoto(getBytesFromFile("temp_img/gy.png"));




        edu.kubsu.fpm.entity.Job job1 = new edu.kubsu.fpm.entity.Job();
        job1.setBeginDate(InsertDate(1,7,2000));
        job1.setEndDate(InsertDate(1,7,2008));
        
        Post post1 = new Post();
        post1.setName("Архитектор");

        Organization organization1 = new Organization();
        organization1.setName("ОАО Тандер");
        organization1.setAdress("Солнечная и Московская");



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
        person2.setPhoto(getBytesFromFile("temp_img/jojo.jpg"));

        personDAO.persist(person2);

        Person person3 = new Person();
        person3.setAdditionalInformation("студент");
        person3.setAdress("");
        person3.setCityOfBirth("");
        person3.setCurrentCity("");
        person3.setCurrentCountry("");
        person3.setDateOfBirth(InsertDate(20, 8, 1991));
        person3.setHomeTel("");
        person3.setIcq("");
        person3.setMobTel("");
        person3.setName("Константин");
        person3.setPatronymic("");
        person3.setSex("");
        person3.setSkype("");
        person3.setSurname("Васильев");
        person3.setWebSite("");
        person3.setPhoto(getBytesFromFile("temp_img/tip.jpg"));

        personDAO.persist(person3);
    }

    private byte[] getBytesFromFile(String fName) {

        try {
            File imgFile = new File(fName);
            Image image = ImageIO.read(new FileInputStream(fName));
            byte[] resisedImg = ImgConverter.changeProportion(imgFile,null, image.getWidth(null), image.getHeight(null), 400, 300);
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
    public void goToPersonalInfo() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("personal_information.jsf");
    }

    public void removeEducation() {
        String educationId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("educationToRemove");
        for(Education education:educations){
            if(education.getId()==Integer.parseInt(educationId)){
                educations.remove(education);
            }
        }
    }
    public void removeJob(){
        String jobId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("jobToRemove");
        for (Job j:jobs){
            if (j.getJobId()==Integer.parseInt(jobId)){
                jobs.remove(j);
            }
        }
    }
    public void changeCityList(){
        String jobId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedCountry");
        for (Job j:jobs){
            if (j.getJobId()==Integer.parseInt(jobId)){
                String s = j.getCountry().getName();
                setCities(j.getCountry().getCities());
            }
        }
    
    }
    public void addEducation(){
        addNewEducationToLst();
    }

    private void addNewEducationToLst() {
        Education e = new Education();
        Date sDate = new Date();
        Date eDate = new Date();
        e.setCity("");
        e.setCountry("");
        e.setDepartment("");
        e.setEnterDate(sDate);
        e.setFaculty("");
        e.setGraduateDate(eDate);
        e.setStatus("");
        e.setUniversity("");
        e.setId(beanUniqueId++);
        educations.add(e);

    }

    public List<City> getCities() {
        if(cities==null){
            cities = new ArrayList<City>();
        }
        if(selectedCountry!=null){
            cities = selectedCountry.getCities();
        }

        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Country> getCountries() {
        if (countries == null){
            countries = new ArrayList<Country>();
//*********************************************************************************************
            Country countryR = new Country();
            countryR.setName("Россия");
            Country countryU = new Country();
            countryU.setName("Украина");
            
            City cityK = new City();
            cityK.setName("Краснодар");
            cityK.setCountry(countryR);

            City cityM = new City();
            cityM.setName("Москва");
            cityM.setCountry(countryR);

            City cityKi = new City();
            cityKi.setName("Киев");
            cityKi.setCountry(countryU);

            List<City> citiesR = new ArrayList<>();
            citiesR.add(cityK);
            citiesR.add(cityM);

            countryR.setCities(citiesR);

            List<City> citiesU= new ArrayList<>();
            citiesU.add(cityKi);

            countryU.setCities(citiesU);

            countryDAO.persist(countryR);
            countryDAO.persist(countryU);
            
//*********************************************************************************************

            countries = countryDAO.getAllCountries();
        }

        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
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


    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
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

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
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

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public Job getTmpJob() {
        return tmpJob;
    }

    public void setTmpJob(Job tmpJob) {
        this.tmpJob = tmpJob;
    }
}
