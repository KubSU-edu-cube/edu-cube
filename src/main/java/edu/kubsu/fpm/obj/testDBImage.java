package edu.kubsu.fpm.obj;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import edu.kubsu.fpm.managed.ConnectionManager;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 25.04.11
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class testDBImage {
    public String putImg(){
      File img = new File("../../tux.png");
      String imgContent = getImageFileContent(img);
      //Blob blob = imgContent.getBytes();
        try {
            //clob.setString(0,imgContent);
            Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return imgContent;
    }

    private String getImageFileContent(File imgFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imgFile);
            byte[] buffer = new byte[10000];
            try {
                fis.read(buffer);
            } catch (IOException ex) {
                Logger.getLogger(testDBImage.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Base64.encode(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(testDBImage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(testDBImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }
}
