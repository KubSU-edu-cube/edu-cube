package edu.kubsu.fpm.entity;

import edu.kubsu.fpm.model.Group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * User: Marina
 * Date: 01.05.12
 * Time: 16:54
 */
@Entity
public class EstimationFunc_Group {

    @EmbeddedId
    protected EstimationFuncGroupPK estimationFuncGroupPK;

    @JoinColumn(name = "FunctionId", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EstimationFunction function;

    @JoinColumn(name = "GroupId", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Group group;

    public EstimationFunc_Group(EstimationFuncGroupPK estimationFuncGroupPK) {
        this.estimationFuncGroupPK = estimationFuncGroupPK;
    }

    public EstimationFunc_Group(int funcid, int groupid){
        this.estimationFuncGroupPK = new EstimationFuncGroupPK(funcid, groupid);
    }

    public EstimationFunc_Group() {
    }

    public EstimationFuncGroupPK getEstimationFuncGroupPK() {
        return estimationFuncGroupPK;
    }

    public void setEstimationFuncGroupPK(EstimationFuncGroupPK estimationFuncGroupPK) {
        this.estimationFuncGroupPK = estimationFuncGroupPK;
    }

    public EstimationFunction getFunction() {
        return function;
    }

    public void setFunction(EstimationFunction function) {
        this.function = function;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
