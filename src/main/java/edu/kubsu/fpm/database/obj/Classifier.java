/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.database.obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iskrich
 */
public class Classifier {
    private int     id;
    private String  name;

    public Classifier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Classifier getFirstClassifierByName(String name, Connection conn){
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    " select * " +
                    "   from app.classifier t" +
                    "  where t.name like(?)");
            pstmt.setString(1, "%"+name+"%");

            ResultSet resSet = pstmt.executeQuery();
            if (resSet.next()){
                return new Classifier(resSet.getInt("ID"),
                                      resSet.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public  static Classifier getClassifierByID(int ID,Connection conn){
        try {
            PreparedStatement statement = conn.prepareStatement(
                                                                "select   cl.ID,cl.\"NAME\" " +
                                                                "from     app.CLASSIFIER cl " +
                                                                "where    cl.ID = (?)");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Classifier(resultSet.getInt("ID"),resultSet.getString("NAME"));

        } catch (SQLException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public  static Classifier getClassifierByName(String name,Connection conn){
        try {
            PreparedStatement statement = conn.prepareStatement(
                                                                "select   cl.ID,cl.\"NAME\" " +
                                                                "from     app.CLASSIFIER cl " +
                                                                "where    cl.\"NAME\" like (?)");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Classifier(resultSet.getInt("ID"), resultSet.getString("NAME"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters And Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // </editor-fold>

    @Override
    public String toString() {
        return this.name;
    }

}
