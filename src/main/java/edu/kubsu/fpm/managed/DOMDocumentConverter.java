/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.managed;

//import edu.za.gui.MathMLTestForm;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DOMDocumentConverter {

    public static InputStream getStreamFromDocument(Document document) {
        {
            try {
                Source source = new DOMSource(document);

                File tempFile = new File("C:\\Windows\\Temp\\XMLTestFile.xml");
                FileOutputStream fos = new FileOutputStream(tempFile);

                Result result = new StreamResult(fos);
                Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(source, result);
                fos.close();

                FileInputStream fis = new FileInputStream(tempFile);

                return fis;
            } catch (IOException ex) {
            Logger.getLogger(DOMDocumentConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            } 
        }
        return null;
    }
    public static  Document getDocumentFromStream(InputStream content){

        try {
            Source source = new StreamSource(content);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Result result = new DOMResult(doc);

            /* Преобразуем источник в результат */
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
            return doc;
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
