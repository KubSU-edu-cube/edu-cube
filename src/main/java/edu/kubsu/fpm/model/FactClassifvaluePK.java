///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package edu.kubsu.fpm.model;
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//
///**
// *
// * @author Марина
// */
//@Embeddable
//public class FactClassifvaluePK implements Serializable {
//    @Basic(optional = false)
//    @Column(name = "FACTID", nullable = false)
//    private int factid;
//    @Basic(optional = false)
//    @Column(name = "CLASSIFID", nullable = false)
//    private int classifid;
//    @Basic(optional = false)
//    @Column(name = "CLASSIF_VALUEID", nullable = false)
//    private int classifValueid;
//
//    public FactClassifvaluePK() {
//    }
//
//    public FactClassifvaluePK(int factid, int classifid, int classifValueid) {
//        this.factid = factid;
//        this.classifid = classifid;
//        this.classifValueid = classifValueid;
//    }
//
//    public int getFactid() {
//        return factid;
//    }
//
//    public void setFactid(int factid) {
//        this.factid = factid;
//    }
//
//    public int getClassifid() {
//        return classifid;
//    }
//
//    public void setClassifid(int classifid) {
//        this.classifid = classifid;
//    }
//
//    public int getClassifValueid() {
//        return classifValueid;
//    }
//
//    public void setClassifValueid(int classifValueid) {
//        this.classifValueid = classifValueid;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (int) factid;
//        hash += (int) classifid;
//        hash += (int) classifValueid;
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof FactClassifvaluePK)) {
//            return false;
//        }
//        FactClassifvaluePK other = (FactClassifvaluePK) object;
//        if (this.factid != other.factid) {
//            return false;
//        }
//        if (this.classifid != other.classifid) {
//            return false;
//        }
//        if (this.classifValueid != other.classifValueid) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "edu.kubsu.fpm.model.FactClassifvaluePK[factid=" + factid + ", classifid=" + classifid + ", classifValueid=" + classifValueid + "]";
//    }
//
//}
