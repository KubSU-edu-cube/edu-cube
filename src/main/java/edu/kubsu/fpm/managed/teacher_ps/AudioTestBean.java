package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.ejb.DBAudioLocal;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 17.05.12
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class AudioTestBean {
    @EJB
    private DBAudioLocal audioLocal;
    private String audioSrc = "localhost:8080/educube-1.0/DBAudioServlet?audioId=";

    public AudioTestBean() {

    }

    public String getAudioSrc()  throws IOException{
        File file = new File("temp_audio/coco_jambo.mp3");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            byte[] buffer = new byte[10485760];
            fis.read(buffer);
            String str = Base64.encode(buffer);
            byte[] result = Base64.decode(str);
            List<byte[]> list = new ArrayList<byte[]>();
            list.add(result);
            audioLocal.setAudioList(list);
            this.audioSrc = audioSrc+"0";
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return audioSrc;
    }

    public void setAudioSrc(String audioSrc) {
        this.audioSrc = audioSrc;
    }
}
