package edu.kubsu.fpm.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: Marina
 * Date: 01.05.12
 * Time: 17:03
 */

@Embeddable
public class EstimationFuncGroupPK implements Serializable {

    private int functionId;
    
    private int groupId;
    
    public EstimationFuncGroupPK(int funcid, int groupid) {
        this.functionId = funcid;
        this.groupId = groupid;
    }

    public EstimationFuncGroupPK() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }
}
