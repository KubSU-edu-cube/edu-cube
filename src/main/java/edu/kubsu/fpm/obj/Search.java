package edu.kubsu.fpm.obj;


import edu.kubsu.fpm.managed.ConnectionManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 21.04.11
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class Search {

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
        query1 = "% "+this.getQueryText()+"%";
        query2 = this.getQueryText()+"%";
        ResultSet resultSet = ConnectionManager.executeQuery(sql, query1, query2, query1, query2, query1, query2, query1, query2);
        try {
            while (resultSet.next()) {
                queryResultList.add(new QueryResult(resultSet.getString("lection"),resultSet.getString("collection"),resultSet.getInt("lectionid")));
            }
        resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return "search";
    }




}
