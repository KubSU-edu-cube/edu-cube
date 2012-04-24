package edu.kubsu.fpm.managed;

import edu.kubsu.fpm.DAO.CityDAO;
import edu.kubsu.fpm.DAO.CountryDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.ejb.DBImageLocal;
import edu.kubsu.fpm.entity.City;
import edu.kubsu.fpm.entity.Country;
import edu.kubsu.fpm.entity.Person;
import edu.kubsu.fpm.managed.classes.DateConverter;
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

    private String selectedFromAge = null;
    private String selectedToAge = null;
    private Country selectedCountry = null;
    private City selectedCity = null;
    
    private List<String> ageList;
    private List<City> cities;
    private List<Country> countries;

    private List<Person> foundPersons;
    private List<PersonalPhoto> smallImgs;

    @EJB
    private PersonDAO personDAO;
    @EJB
    private DBImageLocal imageLocal;
    @EJB
    private CountryDAO countryDAO;
    @EJB
    private CityDAO cityDAO;

    public SearchBean() {
        ageList = new ArrayList<String>();
        initAgeList(0,100);
        shortPersonInfos = new ArrayList<ShortPersonInfo>();
        smallImgs = new ArrayList<PersonalPhoto>();
        foundPersons = new ArrayList<Person>();
    }

    private void initAgeList(int from, int to) {
        for(int i = from; i<=to; i++){
            String val = String.valueOf(i);
            ageList.add(val);
        }
    }

    public void search(){
        // очищаем необходимые списки
        clearLsts();
        // находим всех людей, соответствующих запросу, заполняем список короткой информации, отправляем их фотографии сервлету

//        После перехода, все поля бина = null!!!!!!!!!!!!!!!!!!!
//        this.shortPersonInfos = findPersons(this.simpleQuery,
//                this.selectedCountry,
//                this.selectedCity,
//                this.selectedFromAge,
//                this.selectedToAge,
//                this.sex);
        this.shortPersonInfos = findPersons("Ирина Семенова",
                cities.get(1).getCountry(),
                cities.get(1),
                "18",
                "99",
                "любой");
    }

    private void clearLsts() {
        shortPersonInfos.clear();
        foundPersons.clear();
        smallImgs.clear();
    }

    private List<ShortPersonInfo> findPersons(String simpleQuery, Country selectedCountry, City selectedCity, String selectedFromAge, String selectedToAge, String sex) {
        String[] query = simpleQuery.split(" ");
        String fName;
        String lName;
        if (query.length >= 2){
            fName = query[0];
            lName = query[1];
            // находим всех людей, которые соответствуют запросу
            foundPersons = personDAO.fullSearch(fName,
                    lName,
                    Integer.parseInt(selectedFromAge),
                    Integer.parseInt(selectedToAge),
                    selectedCity.getName(),
                    selectedCountry.getName(),
                    sex);
            // заполняем краткую информацию о найденных людях
            for (Person person: foundPersons){
                ShortPersonInfo shortPersonInfo = new ShortPersonInfo();
                shortPersonInfo.setAge(DateConverter.getAgeByDofBirth(person.getDateOfBirth()));
                shortPersonInfo.setCity(person.getCurrentCity());
                shortPersonInfo.setName(person.getName());
                shortPersonInfo.setSurmane(person.getSurname());
                shortPersonInfo.setSrc(String.valueOf(person.getId()));

                shortPersonInfos.add(shortPersonInfo);

                // добавляем байт-фото в лист фогографий, соответствующих найденным людям
                PersonalPhoto photo = new PersonalPhoto(person.getPhoto(),person.getId());
                smallImgs.add(photo);
            }
            // отправляем лист фотографий сервлету
            imageLocal.setSmallImgs(smallImgs);
            return shortPersonInfos;
        }else {
            return null;
        }
    }

    private void clearShortPersonInfosLst() {
        this.shortPersonInfos.clear();
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

    public String getSelectedFromAge() {
        return selectedFromAge;
    }

    public void setSelectedFromAge(String selectedFromAge) {
        this.selectedFromAge = selectedFromAge;
    }

    public String getSelectedToAge() {
        return selectedToAge;
    }

    public void setSelectedToAge(String selectedToAge) {
        this.selectedToAge = selectedToAge;
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public City getSelectedCity() {

        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public List<String> getAgeList() {
        return ageList;
    }

    public void setAgeList(List<String> ageList) {
        this.ageList = ageList;
    }

    public List<City> getCities() {
        if(countries!=null && selectedCountry!=null){
            cities = cityDAO.getCitiesByCountry(selectedCountry);
        }
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Country> getCountries() {
        if(countries==null){
            countries = countryDAO.getAllCountries();
            if(cities==null){
                cities = new ArrayList<City>();
            }
            for(Country c: countries){
                for (City ci: c.getCities()){
                    cities.add(ci);
                }
            }
        }
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
    public void testListner(){
        String s = selectedCountry.getName();
    }

    public List<Person> getFoundPersons() {
        return foundPersons;
    }

    public void setFoundPersons(List<Person> foundPersons) {
        this.foundPersons = foundPersons;
    }
}

