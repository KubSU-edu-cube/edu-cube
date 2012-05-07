package edu.kubsu.fpm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: Marina
 * Date: 01.05.12
 * Time: 16:23
 */
@Entity
public class EstimationFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String estFunction;

    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL)
    private List<EstimationFunc_Group> estimationFuncGroupList;

    public EstimationFunction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<EstimationFunc_Group> getEstimationFuncGroupList() {
        return estimationFuncGroupList;
    }

    public void setEstimationFuncGroupList(List<EstimationFunc_Group> estimationFuncGroupList) {
        this.estimationFuncGroupList = estimationFuncGroupList;
    }

    public String getFunction() {
        return estFunction;
    }

    public void setFunction(String function) {
        this.estFunction = function;
    }

}
