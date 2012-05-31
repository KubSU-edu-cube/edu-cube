/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.managed.classes;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;


public class DOMDocumentConverter {

    public static InputStream getStreamFromDocument(Document document) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Source xmlSource = new DOMSource(document);
        Result outputTarget = new StreamResult(outputStream);
        
            try {
                TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
            } catch (TransformerException ex) {
                Logger.getLogger(DOMDocumentConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
        return is;
    }

    public static  Document getDocumentFromStream(InputStream content) {
        try {
            Source source = new StreamSource(content);
//            Source source = new StreamSource(new File("C:\\Windows\\Temp\\XMLTestFile.xml"));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Result result = new DOMResult(doc);

            /* Преобразуем источник в результат */
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);

            return doc;

        
        } catch (TransformerException ex) {
            Logger.getLogger(DOMDocumentConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMDocumentConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
