package edu.kubsu.fpm.managed.classes;

import java.util.Calendar;
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
        GregorianCalendar myCalendar = new GregorianCalendar();
        myCalendar.setTime(curDate);
        GregorianCalendar myFromDate = new GregorianCalendar();
        myFromDate.set(myCalendar.get(Calendar.YEAR)-age,myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        return myFromDate.getTime();
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

    public static int getAgeByDofBirth(Date dateOfBirth) {
        GregorianCalendar calendarDoB = new GregorianCalendar();
        calendarDoB.setTime(dateOfBirth);
        GregorianCalendar calendarToday = new GregorianCalendar();
        calendarToday.setTime(new Date());

        int age = calendarToday.get(Calendar.YEAR)-calendarDoB.get(Calendar.YEAR);
        if (calendarToday.get(Calendar.MONTH)<calendarDoB.get(Calendar.MONTH)){
            age = age-1;
        }
        if (calendarToday.get(Calendar.MONTH) == calendarDoB.get(Calendar.MONTH)&&
            calendarToday.get(Calendar.DAY_OF_MONTH) < calendarDoB.get(Calendar.DAY_OF_MONTH)){
            age = age-1;
        }
        return age;
    }
}
