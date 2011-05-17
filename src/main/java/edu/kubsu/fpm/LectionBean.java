package edu.kubsu.fpm;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import edu.kubsu.fpm.aos_general.ConnectionManager;
import edu.kubsu.fpm.database.obj.ColDependFrom;
import edu.kubsu.fpm.database.obj.Fact;
import edu.kubsu.fpm.database.obj.FactCollection;
import edu.kubsu.fpm.ejb.DBImageLocal;
import edu.kubsu.fpm.utils.DOMDocumentConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 11.04.11
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class LectionBean {
    @EJB
    private DBImageLocal DBImage; // сюда будут переданы все картинки, относящиеся к лекции lectionId
    private List<byte[]> byteImgList = new ArrayList<byte[]>();
    private int curImg;
    public String content;
    public boolean  picturePrefered;  //с каким компонентом связать?
    public int difficultie;
    public boolean pictureUnnecessary;
    public int lectionId;
    private List<FactCollection> list;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLectionId() {
        return lectionId;
    }

    public void setLectionId(int lectionId) {
        this.lectionId = lectionId;
    }
    public String buildLection(){
        //очищаем лист картинок, обнуляем счетчик, очищаем наращиваемую строку content
        this.byteImgList.clear();
        this.DBImage.setImgList(byteImgList);
        this.content=" ";
        this.curImg = 0;
        // получили все коллекции, у которых lectionId = this.lectionId
        list = FactCollection.getColFactByItsClassifValue(lectionId,ConnectionManager.getConnection());
        for (FactCollection factCollection : list) {
            if (factCollection.isIsTyped() == false) {
                Insert(factCollection,ConnectionManager.getConnection(),curImg);
            }
        }
        //передаем в ejbean лист с картинками
        this.DBImage.setImgList(byteImgList);
        String temp = this.content;
        return "lection";
    }

    private void Insert(FactCollection factCollection,Connection conn,int curImg) {
        List<FactCollection> depFromColl = ColDependFrom.getNecessaryCollections(factCollection,list , conn);
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
                printFact(factCollection,curImg);
                //если к теореме нашлось доказательство, то выводим его вне очереди//
                if (collection != null){
                printFact(factCollection,curImg);
                }

            } else {
                //если коллекция оказалась не теоремой, то просто выводим//
                printFact(factCollection,curImg);
                System.out.println(factCollection.isIsTyped());
            }
            //если список необходимых невставленных еще не пуст//
        } else {
            for (FactCollection factCollection1 : untypedColl) {
                Insert(factCollection1, conn,curImg);
            }
        }
    }
    public void printFact(FactCollection collection,int curImg) {
//        Создаем DOM документ
        List<Fact> fact = Fact.getFactByCollectionID(collection, ConnectionManager.getConnection());
        Fact f = fact.get(0);
        System.out.println(f.getId());
        // правильно ли получаем?!
        Document byteDocument = DOMDocumentConverter.getDocumentFromStream(f.getContent());
        this.openFact(byteDocument, curImg);

        collection.setIsTyped(true);
    }
    public void openFact(Document byteDocument, int curImg) {

        Element rootEl = byteDocument.getDocumentElement();
        NodeList children = rootEl.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Element currentElement = (Element) children.item(i);
            if (currentElement.getTagName().equals("fact_image")) {

                    /* Выводим картинку */
                    String simage = currentElement.getTextContent();
                    byteImgList.add(curImg, Base64.decode(simage));//складываем картинки в лист
                    this.content = this.content+addImgContent(curImg);//наращиваем строку content
                    curImg = curImg + 1;
            } else if (currentElement.getTagName().equals("fact_text")) {
//                выводим текст//
                String stext = currentElement.getTextContent();  // или эта
                String text = getStringFromByteText(stext.getBytes());// Вот эта строка уже содержимое - текст!!!
                content = content+text;
            }
        }

    }
    public String addImgContent(int curImg) {
        String contextPath = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
//        return "<img src=\"http://localhost:8080/educube-1.0-SNAPSHOT/DBImageServlet?curImg=" + curImg + "\" alt=\"картинка\"/>";
        return "<img src=\"http://localhost:8080"+contextPath+"/DBImageServlet?imgcount=" + curImg + "\" alt=\"картинка\"/>";
    }
    private String getStringFromByteText(byte[] bytes) {
            try {
                InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(bytes));
                BufferedReader br = new BufferedReader(isr);
                StringBuffer buf = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null) {
                    buf.append(line + "\n");
                }
                return buf.toString();
            } catch (IOException ex) {
                Logger.getLogger(FactTestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
}
