package edu.kubsu.fpm.TestingModule;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Марина
 * Date: 17.05.11
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "teacherBean")
@SessionScoped
public class TeacherBean {
    private Connection conn;                                //  Устанавливает соединение с базой
    private List<SelectItem> groupListItem = new ArrayList<SelectItem>();   //  Содержит список всех групп в системе
    private List<SelectItem> courseListItem = new ArrayList<SelectItem>();  //  Содержит список всех курсов в системе
    private Integer selectedGroup = 0;                          //  Содержит выбранную группу
    private Integer selectedCourse = 0;                         //  Содержит выбранный курс
    private Double percent = 0.0;                               //  Процент правильных ответов на обязательные вопросы
    private Integer amountAditionalQuestion = 0;                //  Соответствующее ему число доп. вопросов

    public List<SelectItem> getGroupListItem() {
            //      Получаем список зарегестрированных групп
        Connection conn = getConn();
        groupListItem = new ArrayList<SelectItem>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select id, group_name from app.groups");
            ResultSet resultSet = preparedStatement.executeQuery();
            groupListItem.add(new SelectItem(Long.valueOf(-1L), ""));
            while (resultSet.next()){
                SelectItem selectItem = new SelectItem(resultSet.getInt("id"), resultSet.getString("group_name"));
                groupListItem.add(selectItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return groupListItem;
    }

    public void setGroupListItem(List<SelectItem> groupListItem) {
        this.groupListItem = groupListItem;
    }

    public List<SelectItem> getCourseListItem() {
           //      Получаем список курсов
        Connection conn = getConn();
        courseListItem = new ArrayList<SelectItem>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select id, value from app.classifier_value where classifid = 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            courseListItem.add(new SelectItem(Long.valueOf(-1L), ""));
            while (resultSet.next()){
                SelectItem selectItem = new SelectItem(resultSet.getInt("id"), resultSet.getString("value"));
                courseListItem.add(selectItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return courseListItem;
    }

    public void setCourseListItem(List<SelectItem> courseListItem) {
        this.courseListItem = courseListItem;
    }

    public TeacherBean(){

    }

//      Записывает число дополнительных вопросов для указанной группы и курса
    public String saveAmountAditionalQuestion(){
        Connection conn = getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select id from app.aditional_question " +
                    "where percent_rightansers = " + percent);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
//              Если запись уже есть
                preparedStatement = conn.prepareStatement("update app.aditional_question set quest_amount = " + amountAditionalQuestion
                        + " where id = " + resultSet.getInt("id"));
                preparedStatement.executeUpdate();
            }
            else{
//              Если записи еще нету  //
                preparedStatement = conn.prepareStatement("insert into app.aditional_question (classif_valuesid, groupid, percent_rightansers, quest_amount)" +
                        "values (" + selectedCourse + "," + selectedGroup + "," + percent + "," + amountAditionalQuestion + ")");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "teacher_home";
    }

    public String saveCourse(){
        Connection conn = getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("update app.groups set classif_valuesid = " + selectedCourse
                        + " where id = " + selectedGroup);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "teacher_home";
    }

    public Connection getConn() {

        try{
             Class.forName("org.apache.derby.jdbc.ClientDriver");
             conn = DriverManager.getConnection("jdbc:derby://localhost:1527/FactsStore", "admin", "admin");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return conn;
    }

    public Integer getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Integer selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public Integer getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Integer selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getAmountAditionalQuestion() {
        return amountAditionalQuestion;
    }

    public void setAmountAditionalQuestion(Integer amountAditionalQuestion) {
        this.amountAditionalQuestion = amountAditionalQuestion;
    }
}
