package edu.kubsu.fpm.managed.teacher_ps.classes;

import edu.kubsu.fpm.entity.City;
import edu.kubsu.fpm.entity.Country;

import javax.ejb.EJB;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 06.04.12
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class Job {
    private int jobId;
    private Country country;
    private City city;
    private String organization;
    private String post;
    private int beginYear;
    private int endYear;


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
