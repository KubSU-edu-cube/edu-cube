package edu.kubsu.fpm.managed;
import edu.kubsu.fpm.managed.classes.QueryResult;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 31.05.12
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class Search {
    private String type;
    private String difficultie;
    private String mediaStr;

    public void persistParameters(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("type",this.getType());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("difficultie",this.getDifficultie());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mediaStr",this.getMediaStr());

    }

    public String queryText = "";   // запрос, который введет пользователь в поле ввода на страничке
    public List<QueryResult> queryResultList;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String query1;
    private String query2;
    private String sql = "select  lection,  " +
            "        collection, " +
            "        s2.lectionid " +
            "from " +
            "(select fc.NAME collection, " +
            "       cv.VALUE lection , " +
            "       cv.ID lectionid " +
            "  from app.FACT_COLLECTION fc,  " +
            "       app.COLLFACT_CLASSIFVALUE cfcv, " +
            "       app.CLASSIFIER_VALUE cv " +
            "  where fc.ID = cfcv.COLLID and " +
            "        cfcv.CLASSIF_VALUEID = cv.ID and " +
            "        cfcv.CLASSIFID = 1 and " +
            "        (lower(fc.NAME )like lower(?) or lower(fc.NAME )like lower(?)) " +
            "union " +
            "select '' collection, " +
            "       cv.VALUE lection , " +
            "       cv.ID lectionid " +
            "  from app.CLASSIFIER_VALUE cv " +
            "  where cv.CLASSIFID = 1 and " +
            "        (lower(cv.VALUE )like lower(?) or lower(cv.VALUE )like lower(?)))s3, " +
            "        (select count(lectionid) count,lectionid from " +
            "        (select fc.NAME collection, " +
            "               cv.VALUE lection , " +
            "               cv.ID lectionid " +
            "          from app.FACT_COLLECTION fc,  " +
            "               app.COLLFACT_CLASSIFVALUE cfcv, " +
            "               app.CLASSIFIER_VALUE cv " +
            "          where fc.ID = cfcv.COLLID and " +
            "                cfcv.CLASSIF_VALUEID = cv.ID and " +
            "                cfcv.CLASSIFID = 1 and " +
            "                (lower(fc.NAME )like lower(?) or lower(fc.NAME )like lower(?)) " +
            "        union " +
            "        select '' collection, " +
            "               cv.VALUE lection , " +
            "               cv.ID lectionid " +
            "          from app.CLASSIFIER_VALUE cv " +
            "          where cv.CLASSIFID = 1 and " +
            "                (lower(cv.VALUE )like lower(?) or lower(cv.VALUE )like lower(?)))s1 " +
            "          group by lectionid)s2 " +
            "where   s2.lectionid = s3.lectionid and ((collection not like '' and count>1)or(count=1)) " +
            "order by collection desc ";

    ////////////////////////////////////////////конструктор///////////////////////////////////////////////////////////////////////
    public Search() {
        queryResultList = new ArrayList<QueryResult>();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////getters and setters///////////////////////////////////////////////////////////////

    public List<QueryResult> getQueryResultList() {
        return queryResultList;
    }

    public void setQueryResultList(List<QueryResult> queryResultList) {
        this.queryResultList = queryResultList;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String searchButtonClick() {


        queryResultList.clear();
        query1 = "% " + this.getQueryText() + "%";
        query2 = this.getQueryText() + "%";
        ResultSet resultSet = executeQuery(sql, query1, query2, query1, query2, query1, query2, query1, query2);
        try {
            while (resultSet.next()) {
                queryResultList.add(new QueryResult(resultSet.getString("lection"), resultSet.getString("collection"), resultSet.getInt("lectionid")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return "search";
    }

    public static ResultSet executeQuery(String sql, Object... params) {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/educubeDB", "APP", "APP");
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    statement.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof Integer) {
                    statement.setInt(i + 1, (Integer) params[i]);
                } else if (params[i] instanceof Date) {
                    statement.setDate(i + 1, (Date) params[i]);
                }
            }
            return statement.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficultie() {
        return difficultie;
    }

    public void setDifficultie(String difficultie) {
        this.difficultie = difficultie;
    }

    public String getMediaStr() {
        return mediaStr;
    }

    public void setMediaStr(String mediaStr) {
        this.mediaStr = mediaStr;
    }
}
