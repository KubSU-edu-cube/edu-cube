package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 08.04.12
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Person implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
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

        @OneToMany(mappedBy = "person")
        private List<Education> educations;

        @OneToMany(mappedBy = "person")
        private List<Job> jobs;

}
