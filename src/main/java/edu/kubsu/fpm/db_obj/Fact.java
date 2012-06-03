/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.db_obj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Fact {

    public static void editFactContent(int id, InputStream contentStream, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                        " update app.FACT "
                        + " set content = ? "
                        + " where id = ? ");
            statement.setBlob(1, contentStream);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Fact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected int id;
    protected int colId;
    protected String contentType;
    protected InputStream content;
    protected String difficultie;
    protected int obligatory;

    public Fact(int id, int colId, String contentType, InputStream content, String difficultie, int obligatory) {
        this.id = id;
        this.colId = colId;
        this.contentType = contentType;
        this.content = content;
        this.difficultie = difficultie;
        this.obligatory = obligatory;
    }

    public static List<Fact> getFactByCollectionID(FactCollection collection, Connection conn){
        try {
            List<Fact> facts = new ArrayList<Fact>();
            PreparedStatement statement = conn.prepareStatement(
                    "select f.ID, " +
                            "f.COLLID, " +
                            "f.CONTENT_TYPE, " +
                            "f.CONTENT, " +
                            "f.DIFFICULTIE, " +
                            "fc.\"NAME\", " +
                            " f.OBLIGATORY "+
                      "from app.FACT_COLLECTION fc, " +
                            "app.FACT f " +
                     "where  f.COLLID = fc.ID and " +
                            "f.COLLID = ? ");
            statement.setInt(1, collection.getIdentifier());
            ResultSet resultSet = statement.executeQuery();

            byte[] buffer = new byte[10000];
            while (resultSet.next()) {

                Fact fact = new Fact(
                        resultSet.getInt("id"),
                        resultSet.getInt("collid"),
                        resultSet.getString("content_type"),
                        resultSet.getBlob("content").getBinaryStream(),
                        resultSet.getString("difficultie"),
                        resultSet.getInt("obligatory"));
                
                facts.add(fact);
                return facts;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fact.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void insertFactIntoDB(Fact fact, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    " insert into app.FACT (ID,COLLID,CONTENT_TYPE,CONTENT,DIFFICULTIE, OBLIGATORY) "
                    + "values(?,?,?,?,?,?) ");
            statement.setInt(1, fact.getId());
            statement.setInt(2, fact.getColId());
            statement.setString(3, fact.getContentType());
            statement.setBlob(4, fact.getContent());
            statement.setString(5, fact.getDifficultie());
            statement.setInt(6, fact.getObligatory());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Fact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getColId() {
        return colId;
    }

    public InputStream getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public String getDifficultie() {
        return difficultie;
    }

    public int getId() {
        return id;
    }

    public int getObligatory() {
        return obligatory;
    }

    public void setObligatory(int obligatory) {
        this.obligatory = obligatory;
    }

    public static int getMaxID(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("select max(fc.ID) maxid " + "from app.FACT fc ");
            ResultSet resultSet = statement.executeQuery();
            if (Fact.isntFactEmpty(conn)) {
                resultSet.next();
                int i = resultSet.getInt("maxid");
                return i;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static boolean isntFactEmpty(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("select * from app.FACT");
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(Fact.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
