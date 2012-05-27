/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kubsu.fpm.obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author al
 */
public class FactCollection {

  public  static FactCollection getFactColById(Connection conn, int collId) {
        try {
            PreparedStatement statement = conn.prepareStatement("select * from app.FACT_COLLECTION fc " + "where fc.ID = ? ");
            statement.setInt(1, collId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            FactCollection factCollection = new FactCollection(collId, resultSet.getString("name"));
            return factCollection;
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private int identifier;
    private String name;
    private boolean isTyped;

    public boolean isIsTyped() {
        return isTyped;
    }

    public static String getFactCollType(FactCollection factCollection,Connection conn){
        try {
            PreparedStatement statement = conn.prepareStatement("select s1.value " + "from app.FACT_COLLECTION fc, " + "app.COLLFACT_CLASSIFVALUE cfcv, " + "(select cv.ID,cv.CLASSIFID, " + "cv.\"VALUE\" " + "from app.CLASSIFIER_VALUE cv, " + "app.CLASSIFIER cl " + "where lower(cl.\"NAME\")like lower('тип факта') " + "and cl.ID = cv.CLASSIFID)s1 " + "where s1.classifid = cfcv.CLASSIFID " + "and s1.id = cfcv.CLASSIF_VALUEID " + "and cfcv.COLLID = fc.ID " + "and fc.ID = ? ");
            statement.setInt(1, factCollection.getIdentifier());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("value");
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    public void setIsTyped(boolean isTyped) {
        this.isTyped = isTyped;
    }
    public static FactCollection getFactCollByNameType(String name, String type,Connection conn){
        try {
            //исправить, чтобы искал только среди этой лекции(конкретное значение предметного классификатора)//
            PreparedStatement statement = conn.prepareStatement(
                    "select fc.ID,fc.\"NAME\",s1.value " +
                      "from  app.FACT_COLLECTION fc, " +
                            "app.COLLFACT_CLASSIFVALUE cfcv, " +
                            "(select cv.ID," +
                                    "cv.CLASSIFID, " +
                                    "cv.\"VALUE\" " +
                               "from app.CLASSIFIER_VALUE cv, " +
                                    "app.CLASSIFIER cl " +
                              "where lower(cl.\"NAME\")like lower('тип факта') " +
                                     "and cl.ID = cv.CLASSIFID " +
                                     "and lower(cv.\"VALUE\")like lower(?))s1 " +
                    "where s1.classifid = cfcv.CLASSIFID " +
                           "and s1.id = cfcv.CLASSIF_VALUEID " +
                           "and cfcv.COLLID = fc.ID " +
                           "and lower(fc.\"NAME\") like lower(%?%)) ");
            statement.setString(1, type);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            FactCollection collection;
            if (resultSet.next()){
            collection = new FactCollection(resultSet.getInt("id"), resultSet.getString("name"));
            }else{
                collection = null;
            }
            return collection;
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public FactCollection(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
        this.isTyped = false;
    }
    public static List<FactCollection> getNotTypedColl(List<FactCollection> factCollections,Connection conn){

        List<FactCollection> resultList = new ArrayList<FactCollection>();
        for (FactCollection factCollection : factCollections) {
            if (factCollection.isIsTyped() == false) {
                resultList.add(factCollection);
            }
        }
       return resultList;
    }

    public static void insertFactCollIntoDB(FactCollection factcol, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into app.FACT_COLLECTION(ID,\"NAME\") "
                    + "VALUES (?,?)");
            statement.setInt(1, factcol.getIdentifier());
            statement.setString(2, factcol.getName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<FactCollection> getColFactByClassifIDClassifValues(HashMap<Classifier, ClassifierValue> classifierMap, Connection conn) {
        String collID = "collid";
        String sqlBegin = "select fc.ID, fc.\"NAME\" "
                + " from app.FACT_COLLECTION fc,  "
                + "      ( select sel0.collid from";
        String sqlEnd = " where selAll.collid = fc.ID ";

        String sqlIterable = "             select     cfcv.COLLID, "
                + "                        cfcv.CLASSIFID, "
                + "                        cfcv.CLASSIF_VALUEID "
                + " "
                + "            from        app.COLLFACT_CLASSIFVALUE cfcv, "
                + "                        (select cv.ID,cv.CLASSIFID,cv.\"VALUE\" "
                + "                        from app.CLASSIFIER_VALUE cv, app.CLASSIFIER cl "
                + "                        where cv.CLASSIFID = cl.ID and cl.\"NAME\" = ? and cv.\"VALUE\" = ?) s1 "
                + " "
                + "            where       s1.id = cfcv.CLASSIF_VALUEID";
        /* Собираемая строка */
        String sql = new String();
        sql = sql.concat(sqlBegin);

        if (classifierMap.size() == 0) {
            return null;
        } else if (classifierMap.size() == 1) {
            sql = sql.concat("( " + sqlIterable + " ) sel ");
            sql = sql.concat(" where sel.collid = fc.ID ");
        } else {
            for (int i = 0; i < classifierMap.keySet().size(); i++) {
                String comma = (i + 1 < classifierMap.keySet().size() ? ", " : " ");
                sql = sql.concat("( " + sqlIterable + " ) sel" + i + comma);
            }

            sql = sql.concat(" where ");
            for (int i = 1; i < classifierMap.keySet().size(); i++) {
                String and = (i == 1 ? "" : " and ");
                sql = sql.concat(and + " sel" + (i - 1) + "." + collID + " = sel" + i + "." + collID + " ");
            }
            sql = sql.concat(") selAll ");
            sql = sql.concat(sqlEnd);
        }

        Iterator<Classifier> iterator = classifierMap.keySet().iterator();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int i = 1;
            while (iterator.hasNext()) {
                Classifier classifier = iterator.next();
                pstmt.setString(i, classifier.getName());
                pstmt.setString(i + 1, classifierMap.get(classifier).getValue());
                i += 2;
            }

            ResultSet resSet = pstmt.executeQuery();
            List<FactCollection> result = new ArrayList();
            while (resSet.next()) {
                FactCollection newFact = new FactCollection(resSet.getInt("ID"),
                        resSet.getString("Name"));
                result.add(newFact);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static List<ClassifierValue> getClassifierValuesByFactColClassifier(FactCollection factCollection, Classifier classifier, Connection conn) {

        try {
            List<ClassifierValue> list = new ArrayList<ClassifierValue>();
            PreparedStatement statement = conn.prepareStatement(
                    " select  clv.ID,clv.CLASSIFID,clv.\"VALUE\",clv.PARENTID "
                    + "from    app.CLASSIFIER_VALUE clv, "
                    + "(select  cfcv.COLLID, cfcv.CLASSIFID, cfcv.CLASSIF_VALUEID "
                    + "from    app.COLLFACT_CLASSIFVALUE cfcv, "
                    + "(select cl.ID, cl.\"NAME\" "
                    + "from app.CLASSIFIER cl "
                    + "where cl.\"NAME\" like ?)s1, "
                    + "(select fc.ID, fc.\"NAME\" "
                    + "from app.FACT_COLLECTION fc "
                    + "where fc.\"NAME\" like ?)s2 "
                    + "where cfcv.COLLID = s2.ID and cfcv.CLASSIFID = s1.ID) s3 "
                    + "where s3.CLASSIF_VALUEID = clv.ID ");
            statement.setString(1, classifier.toString());
            statement.setString(2, factCollection.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new ClassifierValue(
                        resultSet.getInt("ID"),
                        resultSet.getString("VALUE"),
                        resultSet.getInt("CLASSIFID"),
                        resultSet.getInt("PARENTID")));
            }
            return list;


        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static List<FactCollection> getColFactByItsClassifValue(ClassifierValue classifierValue, Connection conn) {


        try {
            List<FactCollection> list = new ArrayList<FactCollection>();
            PreparedStatement statement = conn.prepareStatement(
                    "select  fc.ID, "
                    + "fc.\"NAME\" "
                    + "from app.FACT_COLLECTION fc, "
                    + "(select cfcv.COLLID "
                    + "from app.COLLFACT_CLASSIFVALUE cfcv "
                    + "where cfcv.CLASSIF_VALUEID = ?)s1 "
                    + "where s1.collid = fc.ID ");

            statement.setInt(1, classifierValue.getValueId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                FactCollection factCollection = new FactCollection(resultSet.getInt("ID"), resultSet.getString("NAME"));
                list.add(factCollection);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static List<FactCollection> getColFactByItsClassifValue(int lectionId, Connection conn) {


        try {
            List<FactCollection> list = new ArrayList<FactCollection>();
            PreparedStatement statement = conn.prepareStatement(
                    "select  fc.ID, "
                    + "fc.\"NAME\" "
                    + "from app.FACT_COLLECTION fc, "
                    + "(select cfcv.COLLID "
                    + "from app.COLLFACT_CLASSIFVALUE cfcv "
                    + "where cfcv.CLASSIF_VALUEID = ?)s1 "
                    + "where s1.collid = fc.ID ");

            statement.setInt(1,lectionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                FactCollection factCollection = new FactCollection(resultSet.getInt("ID"), resultSet.getString("NAME"));
                list.add(factCollection);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FactCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static FactCollection getFactColByName(String name, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select   fc.ID,fc.\"NAME\" "
                    + "from     app.FACT_COLLECTION fc "
                    + "where    fc.\"NAME\" like (?) ");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new FactCollection(resultSet.getInt("ID"), resultSet.getString("NAME"));

        } catch (SQLException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<FactCollection> getFactColListByName(String name, Connection conn) {
        try {
            List<FactCollection> list = new ArrayList<FactCollection>();
            PreparedStatement statement = conn.prepareStatement(
                    "select   fc.ID,fc.\"NAME\" "
                    + "from     app.FACT_COLLECTION fc "
                    + "where    lower(fc.\"NAME\") like lower(?) ");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new FactCollection(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // </editor-fold>
    @Override
    public String toString() {
        return this.name;
    }

     public static int getMaxID(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "select max(fc.ID) maxid " +
                    "from app.FACT_COLLECTION fc ");
            ResultSet resultSet = statement.executeQuery();
            if (FactCollection.isntFactEmpty(conn)) {
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
            PreparedStatement statement = conn.prepareStatement("select * from app.FACT_COLLECTION");
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(Fact.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
