package edu.kubsu.fpm.managed.teacher_ps.classes;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 06.04.12
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class Job {
    private String country;
    private String city;
    private String organization;
    private String post;
    private String beginDate;
    private String endDate;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
