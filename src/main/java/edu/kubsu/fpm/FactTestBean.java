package edu.kubsu.fpm;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import edu.kubsu.fpm.aos_general.ConnectionManager;
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 04.04.11
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class FactTestBean {
     @EJB
     private DBImageLocal DBImage;
     private String separator;
     private String content;
     private int imgCount;
     private List<byte[]> imgList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FactTestBean() {

        this.imgList = new ArrayList<byte[]>();
        imgCount = 0;
        separator = System.getProperty("file.separator");
        content = " ";
    }

    public String testServlet(){
        //DBImage.setMyStr("Строка, заданная из test_class бина");
        return "search";
    }
    public static byte[] getBytesFromFile(File file) throws IOException {
            InputStream is = new FileInputStream(file);
            // Get the size of the file
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                // File is too large
            }
            // Create the byte array to hold the data
            byte[] bytes = new byte[(int) length];
            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            // Close the input stream and return bytes
            is.close();
            return bytes;
        }
    public String addImgContent(int imgCount){
      return "<img src=\"http://localhost:8080/edukub-1.0-SNAPSHOT/DBImageServlet?imgcount="+imgCount+"\" alt=\"картинка\"/>";
    }

    public String testImgServlet(){
        this.imgList.clear();
        this.DBImage.setImgList(this.imgList);
        this.content = "";
        this.imgCount=0;

//        File file1 = new File("D:"+separator+"imgServTest"+separator+"img1.png");
//        File file2 = new File("D:"+separator+"imgServTest"+separator+"img2.png");
//        File file3 = new File("D:"+separator+"imgServTest"+separator+"img3.png");

        //            byte[] photo1 = getBytesFromFile(file1);
//            byte[] photo2 = getBytesFromFile(file2);
//            byte[] photo3 = getBytesFromFile(file3);
//            this.imgList.add(0,photo1);
//            this.imgList.add(1,photo2);
//            this.imgList.add(2,photo3);
        FactCollection col = new FactCollection(11,"img_plus");
        printFact(col,imgCount);
        DBImage.setImgList(imgList);
//            this.content = this.content+addImgContent(0);
//            this.content = this.content+addImgContent(1);
//            this.content = this.content+addImgContent(2);
        return "lection";
    }
    public void printFact(FactCollection collection,int imgCount) {
//        Создаем DOM документ
        List<Fact> fact = Fact.getFactByCollectionID(collection, ConnectionManager.getConnection());
        Fact f = fact.get(0);
        System.out.println(f.getId());
        // правильно ли получаем?!
        Document byteDocument = DOMDocumentConverter.getDocumentFromStream(f.getContent());
        this.openFact(byteDocument, imgCount);

        collection.setIsTyped(true);
    }
    public void openFact(Document byteDocument, int imgCount) {

        Element rootEl = byteDocument.getDocumentElement();
        NodeList children = rootEl.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Element currentElement = (Element) children.item(i);
            if (currentElement.getTagName().equals("fact_image")) {

                    /* Выводим картинку */
                    String simage = currentElement.getTextContent();
                    imgList.add(imgCount, Base64.decode(simage));//складываем картинки в лист
                    this.content = this.content+addImgContent(imgCount);//наращиваем строку content
                    imgCount = imgCount + 1;
            } else if (currentElement.getTagName().equals("fact_text")) {
//                выводим текст//
                String stext = currentElement.getTextContent();  // или эта
                String text = getStringFromByteText(stext.getBytes());// Вот эта строка уже содержимое - текст!!!
                content=content+text;
                String a = text;
            }
        }

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
