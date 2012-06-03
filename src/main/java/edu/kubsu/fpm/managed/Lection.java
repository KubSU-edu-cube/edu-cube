package edu.kubsu.fpm.managed;

import edu.kubsu.fpm.db_obj.ColDependFrom;
import edu.kubsu.fpm.db_obj.Fact;
import edu.kubsu.fpm.db_obj.FactCollection;
import edu.kubsu.fpm.db_obj.MediaContent;
import edu.kubsu.fpm.ejb.DBAudioLocal;
import edu.kubsu.fpm.ejb.DBImageLocal;
import edu.kubsu.fpm.ejb.DBVideoLocal;
import edu.kubsu.fpm.managed.classes.DOMDocumentConverter;
import edu.kubsu.fpm.managed.classes.media_classes.Audio;
import edu.kubsu.fpm.managed.classes.media_classes.Image;
import edu.kubsu.fpm.managed.classes.media_classes.Video;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.font.TrueTypeFont;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 11.04.11
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class Lection {
    private String type="подробно";
    private String mediaStr = "максимально";



    @EJB
    private DBImageLocal DBImage;
    @EJB
    private DBAudioLocal DBAudio;
    @EJB
    private DBVideoLocal DBVideo;

    private List<Image> byteImgList = new ArrayList<Image>();
    private List<Audio> byteAudioList = new ArrayList<Audio>();
    private List<Video> byteVideoList = new ArrayList<Video>();

    public String content;
    public boolean picturePrefered;  //с каким компонентом связать?
    public String difficultie;
    public boolean pictureUnnecessary;
    private int shortly;
    private int mediaOn;

    public int lectionId;
    private List<FactCollection> list;

    private Connection conn;

    public String buildLection() {
        fillParameters(
        (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("type"),
        (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("difficultie"),
        (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mediaStr"));


        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/educubeDB", "APP", "APP");
            this.conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.lectionId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lectionId"));

        InitLection();
        // получили все коллекции, у которых lectionId = this.lectionId
        list = FactCollection.getColFactByItsClassifValue(lectionId, conn);
        for (FactCollection factCollection : list) {
            if (factCollection.isIsTyped() == false) {
                Insert(factCollection, conn);
            }
        }
        //передаем в ejbean лист с картинками
        this.DBImage.setImgList(byteImgList);
        this.DBAudio.setAudioList(byteAudioList);
        this.DBVideo.setVideoList(byteVideoList);
        String temp = this.content;
        this.content = temp;
        return "lection";
    }

    private void fillParameters(String type, String difficultie, String mediaStr) {
        if(type.equals("сжато")){
            this.shortly=1;
        }else{
            this.shortly=0;
        }
        this.difficultie=difficultie;
        if(mediaStr.equals("максимально")){
            this.mediaOn=1;
        }else{
            this.mediaOn=0;
        }
    }

    private void InitLection() {
        this.byteImgList.clear();
        this.byteAudioList.clear();
        this.byteAudioList.clear();
        this.DBImage.setImgList(byteImgList);
        this.DBAudio.setAudioList(byteAudioList);
        this.DBVideo.setVideoList(byteVideoList);
        this.content = "";
    }

    private void Insert(FactCollection factCollection, Connection conn) {
        List<FactCollection> depFromColl = ColDependFrom.getNecessaryCollections(factCollection, list, conn);
        List<FactCollection> untypedColl = FactCollection.getNotTypedColl(depFromColl, conn);
        if (untypedColl.isEmpty()) {
            //если вдруг это теорема, то найти её доказательство//
            //получаем значение типового классификатора нашей коллекции//
            String collType = FactCollection.getFactCollType(factCollection, conn);
            if (collType.equals("теорема")
                    || collType.equals("лемма")
                    || collType.equals("следствие")
                    || collType.equals("свойство")) {
                //ищем доказательство к теореме//
                FactCollection collection = FactCollection.getFactCollByNameType
                        (factCollection.getName(), "доказательство", conn);
                //выводим саму теорему//
                printFact(factCollection);
                //если к теореме нашлось доказательство, то выводим его вне очереди//
                if (collection != null) {
                    printFact(factCollection);
                }

            } else {
                //если коллекция оказалась не теоремой, то просто выводим//
                printFact(factCollection);
                System.out.println(factCollection.isIsTyped());
            }
            //если список необходимых невставленных еще не пуст//
        } else {
            for (FactCollection factCollection1 : untypedColl) {
                Insert(factCollection1, conn);
            }
        }
    }

    public void printFact(FactCollection collection) {

        List<Fact> fact = Fact.getFactByCollectionID(collection, conn);
        // Здесь должен быть выбор подходящего факта из коллекции
        Fact f = fact.get(0);
        if (factCorrresponds(f)){
            Document document = DOMDocumentConverter.getDocumentFromStream(f.getContent());
            List<MediaContent> factMedias = MediaContent.getMediaContentList(f.getId(), conn);
            this.openFact(document, factMedias);
        }
        collection.setIsTyped(true);
    }

    private boolean factCorrresponds(Fact f) {
        if (this.shortly==0){
            if(f.getContentType().equals("audio")||f.getContentType().equals("video")||f.getContentType().equals("image")){
                if(f.getObligatory()==1){
                    return true;
                }
                if(this.mediaOn==0){
                    return false;
                }
            }
            return true;
        }
        if(this.difficultie.equals("сложная")&&f.getDifficultie().equals("легкая") && f.getObligatory()==0){
            return false;
        }
        if(this.shortly==1){
            if(f.getObligatory()==0){
                return false;
            }
        }
        return true;


    }

    public void openFact(Document doc, List<MediaContent> factMedias) {

        Element rootElement = doc.getDocumentElement();
        NodeList node = rootElement.getChildNodes();
        for (int i = 0; i < node.getLength(); i++) {
            Node childNode = node.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElmnt = (Element) childNode;
                if (childElmnt.getTagName().equals("fact_text")) {
                    String textContent = childElmnt.getTextContent();
                    content = content + textContent;
                }
                if (childElmnt.getTagName().equals("fact_image")) {
                    try {
                        // Узнали, какой идентификатор у записи в таблице MediaContent где хранится картинка
                        int mediaId = Integer.parseInt(childElmnt.getTextContent());
                        // Выбрали необходимый медиаконтент из списка всех медиа выбранного факта
                        MediaContent mc = getMCById(factMedias, mediaId);
                        Image image = new Image(mediaId, IOUtils.toByteArray(mc.getContent()));
                        // Наращиваем содержимое content
                        content = content + addImgContent(mediaId);
                        // Добавляем картинку в лист всех картинок лекции
                        byteImgList.add(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (childElmnt.getTagName().equals("fact_audio")) {

                        // Узнали, какой идентификатор у записи в таблице MediaContent где хранится аудио
                        int mediaId = Integer.parseInt(childElmnt.getTextContent());
                        // Выбрали необходимый медиаконтент из списка всех медиа выбранного факта
                        MediaContent mc = getMCById(factMedias, mediaId);
                    Audio audio = null;
                    try {
                        audio = new Audio(mediaId, IOUtils.toByteArray(mc.getContent()));
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    // Наращиваем содержимое content
                        content = content + addAudioContent(mediaId);
                        // Добавляем картинку в лист всех картинок лекции
                        byteAudioList.add(audio);

                }
                if (childElmnt.getTagName().equals("fact_math")) {
                    String mathContent = childElmnt.getTextContent();
                    content = content + mathContent;
                }
                if (childElmnt.getTagName().equals("fact_video")) {
                    try {
                        // Узнали, какой идентификатор у записи в таблице MediaContent где хранится видео
                        int mediaId = Integer.parseInt(childElmnt.getTextContent());
                        // Выбрали необходимый медиаконтент из списка всех медиа выбранного факта
                        MediaContent mc = getMCById(factMedias, mediaId);
                        Video video = new Video(mediaId, IOUtils.toByteArray(mc.getContent()));
                        // Наращиваем содержимое content
                        content = content + addVideoContent(mediaId);
                        // Добавляем картинку в лист всех картинок лекции
                        byteVideoList.add(video);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    private MediaContent getMCById(List<MediaContent> factMedias, int mediaId) {
        for (MediaContent mediaContent : factMedias) {
            if (mediaContent.getId() == mediaId) {
                return mediaContent;
            }
        }
        return null;
    }

    private String addAudioContent(int curAudio) {
        return
                "<object type=\"application/x-shockwave-flash\" data=\"dewplayer/dewplayer-mini.swf\" width=\"160\" height=\"20\"  " +
                        "id=\"dewplayer\" name=\"dewplayer\"> " +
                        "<param name=\"wmode\" value=\"transparent\"/> " +
                        "<param name=\"movie\" value=\"dewplayer/dewplayer-mini.swf\"/> " +
                        "<param name=\"flashvars\" value=\"mp3=http://localhost:8080/educube-1.0/DBAudioServlet?audioId=" + curAudio + "&amp;autostart=1\"/> " +
                        "</object> ";
    }

    private String addVideoContent(int videoId) {
        return

        "<script src=\"flowplayer/flowplayer-3.2.10.min.js\" type=\"text/javascript\"></script>\n" +
                "            <a href=\"http://localhost:8080/educube-1.0/DBVideoServlet?videoId=" + videoId +"\"" +
                "               style=\"display:block;width:425px;height:300px;margin-left:200px\"\n" +
                "               id=\"player\">\n" +
                "            </a>\n" +
                "            <script language=\"JavaScript\" type=\"text/javascript\">\n" +
                "                flowplayer(\"player\", \"flowplayer/flowplayer-3.2.11.swf\");\n" +
                "            </script>";
    }

    public String addImgContent(int curImg) {
        return "<img src=\"http://localhost:8080/educube-1.0/DBImageServlet?imageId=" + curImg + "\" alt=\"картинка\"/>";
    }

    public List<Image> getByteImgList() {
        return byteImgList;
    }

    public void setByteImgList(List<Image> byteImgList) {
        this.byteImgList = byteImgList;
    }

    public List<Audio> getByteAudioList() {
        return byteAudioList;
    }

    public void setByteAudioList(List<Audio> byteAudioList) {
        this.byteAudioList = byteAudioList;
    }

    public List<Video> getByteVideoList() {
        return byteVideoList;
    }

    public void setByteVideoList(List<Video> byteVideoList) {
        this.byteVideoList = byteVideoList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPicturePrefered() {
        return picturePrefered;
    }

    public void setPicturePrefered(boolean picturePrefered) {
        this.picturePrefered = picturePrefered;
    }

    public int getShortly() {
        return shortly;
    }

    public void setShortly(int shortly) {
        this.shortly = shortly;
    }

    public String getDifficultie() {
        return difficultie;
    }

    public void setDifficultie(String difficultie) {
        this.difficultie = difficultie;
    }

    public boolean isPictureUnnecessary() {
        return pictureUnnecessary;
    }

    public void setPictureUnnecessary(boolean pictureUnnecessary) {
        this.pictureUnnecessary = pictureUnnecessary;
    }

    public int getLectionId() {
        return lectionId;
    }

    public void setLectionId(int lectionId) {
        this.lectionId = lectionId;
    }

    public List<FactCollection> getList() {
        return list;
    }

    public void setList(List<FactCollection> list) {
        this.list = list;
    }

    public int getMediaOn() {
        return mediaOn;
    }

    public void setMediaOn(int mediaOn) {
        this.mediaOn = mediaOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
