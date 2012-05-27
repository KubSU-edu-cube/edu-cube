/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class FactClassifValues {

    protected int factId;
    protected int classifId;
    protected int classifValueId;

    public FactClassifValues(int factId, int classifId, int classifValueId) {
        this.factId = factId;
        this.classifId = classifId;
        this.classifValueId = classifValueId;
    }

    public int getClassifId() {
        return classifId;
    }

    public int getClassifValueId() {
        return classifValueId;
    }

    public int getFactId() {
        return factId;
    }

    public static void insertFactClassifValuesToDB(FactClassifValues classifValues, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into app.FACT_CLASSIFVALUE (FACTID,CLASSIFID,CLASSIF_VALUEID) " +
                    "VALUES (?,?,?) ");
            statement.setInt(1, classifValues.getFactId());
            statement.setInt(2, classifValues.getClassifId());
            statement.setInt(3, classifValues.getClassifValueId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FactClassifValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
