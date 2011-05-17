package edu.kubsu.fpm.database.obj;

/**
 * Created by IntelliJ IDEA.
 * User: Анна
 * Date: 23.04.11
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */

public class QueryResult {
    private String lection;
    private String collection;
    private int lectionId;

    public QueryResult(String lection, String collection, int lectionId) {
        this.lection = lection;
        this.collection = collection;
        this.lectionId = lectionId;
    }

    public String getLection() {
        return lection;
    }

    public void setLection(String lection) {
        this.lection = lection;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public int getLectionId() {
        return lectionId;
    }

    public void setLectionId(int lectionId) {
        this.lectionId = lectionId;
    }
}
