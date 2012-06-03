package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.ejb.DBAudioLocal;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class AudioTestBean {
    @EJB
    private DBAudioLocal audioLocal;
    private String audioSrc = "localhost:8080/educube-1.0/DBAudioServlet?audioId=";
    private StreamedContent streamMusic;

    public AudioTestBean() {
    }

    public StreamedContent getStreamMusic() {

        return streamMusic;
    }

    public void setAudioSrc(String audioSrc) {
        this.audioSrc = audioSrc;
    }
}
