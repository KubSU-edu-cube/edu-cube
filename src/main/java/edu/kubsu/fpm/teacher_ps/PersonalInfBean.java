package edu.kubsu.fpm.teacher_ps;

import edu.kubsu.fpm.teacher_ps.classes.Education;
import edu.kubsu.fpm.teacher_ps.classes.Job;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 06.04.12
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class PersonalInfBean {
    private String name;
    private String surname;
    private String patronymic;
    private String dateOfBirth;
    private String sex;
    private String cityOfBirth;
    private String currentCountry;
    private String currentCity;
    private String Adress;
    private String mobTel;
    private String homeTel;
    private String skype;
    private String icq;
    private String webSite;
    private String additionalInformation;
    private List<Education> educations;
    private List<Job> jobs;

    public PersonalInfBean() {
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
        e.setId(educations.size());
        educations.add(e);
        
    }

    public void removeEducation() {
        String educationId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("educationToRemove");
        educations.remove(Integer.parseInt(educationId) - 1);
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
        e.setId(educations.size());
        educations.add(e);

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

    public String getDateOfBirth() {
        return dateOfBirth;
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

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
