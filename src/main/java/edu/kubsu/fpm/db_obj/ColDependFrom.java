/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kubsu.fpm.db_obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ColDependFrom {

    protected int colId;
    protected int depColId;

    public ColDependFrom(int colId, int depColId) {
        this.colId = colId;
        this.depColId = depColId;
    }
    public static List<FactCollection> getNecessaryCollections(FactCollection collection,
            List<FactCollection> lectionList,
            Connection conn){
        try {
            List<FactCollection> resultList = new ArrayList<FactCollection>();
            PreparedStatement statement = conn.prepareStatement(
                    "select cdf.COLLID ,cdf.DEPENDENT_COLLID  " +
                    "from app.COLL_DEPEND_FROM cdf " +
                    "where cdf.DEPENDENT_COLLID = ? ");
            statement.setInt(1, collection.getIdentifier());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int collId = resultSet.getInt("COLLID");
                for (FactCollection factCollection : lectionList) {
                    if (factCollection.getIdentifier() == collId) {
                        resultList.add(factCollection);
                    }
                }
            }
            return resultList;
        } catch (SQLException ex) {
            Logger.getLogger(ColDependFrom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getColId() {
        return colId;
    }

    public int getDepColId() {
        return depColId;
    }
    public static void  insertColDepFromToDB(ColDependFrom dependFrom, Connection conn){
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into app.COLL_DEPEND_FROM (COLLID,DEPENDENT_COLLID) " +
                    "VALUES (?,?) ");
            statement.setInt(1, dependFrom.getColId());
            statement.setInt(2, dependFrom.getDepColId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ColDependFrom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

