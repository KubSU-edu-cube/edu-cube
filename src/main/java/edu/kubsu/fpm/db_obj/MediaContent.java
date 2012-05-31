/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.db_obj;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * @author anna
 */
public class MediaContent {
    
    private int id;
    private int factId;
    private String contentName;
    private InputStream content;

    public MediaContent(int factId, String contentName, InputStream content) {
        this.factId = factId;
        this.contentName = contentName;
        this.content = content;
    }

    public MediaContent(int id, int factId, String contentName, InputStream content) {
        this.id = id;
        this.factId = factId;
        this.contentName = contentName;
        this.content = content;
    }
    
    
    public static void insertMediaIntoDB(MediaContent mediaContent, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    " insert into app.MEDIACONTENT (FACT_ID, CONTENT_NAME, CONTENT) "
                    + "values(?,?,?) ");
            statement.setInt(1, mediaContent.getFactId());
            statement.setString(2, mediaContent.getContentName());
            statement.setBlob(3, mediaContent.getContent());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Fact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int getMaxID(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select max(mc.ID) maxid " + "from app.MEDIACONTENT mc ");
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
    public static List<MediaContent> getMediaContentList(int factId, Connection conn){
        List<MediaContent> contents = new ArrayList<MediaContent>();
        try {
            PreparedStatement statement = conn.prepareStatement
                    ("select * from app.MEDIACONTENT mc where mc.FACT_ID = ? ");
            statement.setInt(1, factId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                MediaContent mc = new MediaContent(
                        resultSet.getInt("ID"),
                        resultSet.getInt("FACT_ID"),
                        resultSet.getString("CONTENT_NAME"),
                        resultSet.getBlob("CONTENT").getBinaryStream());
                contents.add(mc);
            }
            return contents;
        } catch (SQLException ex) {
            Logger.getLogger(MediaContent.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }




    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public int getFactId() {
        return factId;
    }

    public void setFactId(int factId) {
        this.factId = factId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
