package edu.kubsu.fpm.entity.lection_classification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 01.05.12
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Coll_Depend_From implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


}
