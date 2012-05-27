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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iskrich
 */
public class ClassifierValue {

    private Classifier classifier;
    private int valueId;
    private String value;
    private int classifID;
    private int parentID;

    public ClassifierValue(int valueId, String value, int classifID, int parentID) {
        this.valueId = valueId;
        this.value = value;
        this.classifID = classifID;
        this.parentID = parentID;
    }

    public ClassifierValue(Classifier classifier, int valueId, String value) {
        this.classifier = classifier;
        this.valueId = valueId;
        this.value = value;
    }

    public static ClassifierValue getFirstClassifValueByClassifierAndParentID(Classifier classifier,
                                                                              Integer parentID,
                                                                              Connection conn) {
        List<ClassifierValue> resultList = getClassifierValuesByClassifierAndParentID(classifier, parentID, conn);
        return (resultList != null && resultList.size() >= 1) ? resultList.get(0) : null;
    }


    public static List<ClassifierValue> getClassifierValuesByClassifierAndParentID(Classifier classifier,
                                                                                   Integer parentID,
                                                                                   Connection conn) {
        try {
            PreparedStatement pstmt;
            if (parentID != null) {
                pstmt = conn.prepareStatement(
                        " select * " +
                        "   from app.classifier_value cv " +
                        "  where cv.classifID = ? " +
                        "    and cv.parentID = ? ");
                pstmt.setInt(2, parentID);
            } else {
                pstmt = conn.prepareStatement(
                        " select * " +
                        "   from app.classifier_value cv " +
                        "  where cv.classifID = ? " +
                        "    and cv.parentID is null ");
            }
            pstmt.setInt(1, classifier.getId());

            List<ClassifierValue> result = new ArrayList<ClassifierValue>();
            ResultSet resSet = pstmt.executeQuery();
            while (resSet.next()){
                result.add(new ClassifierValue(classifier, resSet.getInt("ID"), resSet.getString("Value")));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ClassifierValue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<ClassifierValue> getClassValWithColByClassValue(String classValue,String className,Connection conn){
        try {
            List<ClassifierValue> list = new ArrayList<ClassifierValue>();
            PreparedStatement statement = conn.prepareStatement(
                                            "select distinct  "+
                                                   "s2.id, "+
                                                   "s2.classifid, "+
                                                   "s2.value, "+
                                                   "s2.parentid "+
                                              "from app.COLLFACT_CLASSIFVALUE cfcv, "+
                                                   "( select clv.ID, "+
                                                            "clv.CLASSIFID, "+
                                                            "clv.\"VALUE\", "+
                                                            "clv.PARENTID "+
                                                       "from app.CLASSIFIER_VALUE clv, "+
                                                            "(select cl.ID "+
                                                               "from app.CLASSIFIER cl "+
                                                              "where cl.\"NAME\" like ?) s1 "+
                                                      "where s1.ID = clv.CLASSIFID ) s2 "+
                                             "where cfcv.CLASSIFID = s2.classifid "+
                                               "and cfcv.CLASSIF_VALUEID = s2.ID "+
                                               "and lower(s2.value) like lower(?)");
            statement.setString(1, "%"+className+"%");
            statement.setString(2, "%"+classValue+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String value = resultSet.getString("VALUE");
                int classifid = resultSet.getInt("CLASSIFID");
                int parentid = resultSet.getInt("PARENTID");
                list.add(new ClassifierValue(id, value, classifid, parentid));

            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ClassifierValue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters And Setters">
    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    // </editor-fold>

    @Override
    public String toString() {
        return this.value;
    }
}
