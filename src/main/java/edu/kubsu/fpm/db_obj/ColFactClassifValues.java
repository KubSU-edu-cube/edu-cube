/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.db_obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ColFactClassifValues {

     protected int collId;
    protected int classifId;
    protected int classifValueId;

    public ColFactClassifValues(int collId, int classifId, int classifValueId) {
        this.collId = collId;
        this.classifId = classifId;
        this.classifValueId = classifValueId;
    }

    public int getClassifId() {
        return classifId;
    }

    public int getClassifValueId() {
        return classifValueId;
    }

    public int getcollId() {
        return collId;
    }

    public static void insertColFactClassifValuesToDB(ColFactClassifValues classifValues, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into app.COLLFACT_CLASSIFVALUE (COLLID,CLASSIFID,CLASSIF_VALUEID) " +
                    "VALUES (?,?,?) ");
            statement.setInt(1, classifValues.getcollId());
            statement.setInt(2, classifValues.getClassifId());
            statement.setInt(3, classifValues.getClassifValueId());
            statement.executeUpdate();
            //System.out.println(statement.executeUpdate());
        } catch (SQLException ex) {
            Logger.getLogger(ColFactClassifValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
