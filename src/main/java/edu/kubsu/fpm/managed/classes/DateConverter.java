package edu.kubsu.fpm.managed.classes;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 23.04.12
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class DateConverter {
    public static Date getDate(Date curDate, int age){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(age,0,0);
        Date date = new Date();
        date.setTime(calendar.getTime().getTime()-curDate.getTime());
        return date;
    }
    public static boolean isBetweenAges(Date dOfBirth, int ageFrom, int ageTo){
        return dOfBirth.after(DateConverter.getDate(new Date(),ageTo))&&
               dOfBirth.before(DateConverter.getDate(new Date(),ageFrom));
    }
    public static Date getCurDate(){
        return new Date();
    }
    public static Date getMinDate(){
        Date d = new Date();
        d.setTime(0);
        return d;
    }

    public static Date getMaxDate() {
        return new Date();
    }
}
