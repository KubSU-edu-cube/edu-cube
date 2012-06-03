package edu.kubsu.fpm.managed.classes;



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

