package edu.kubsu.fpm.managed.adaptiveTesting;

import edu.kubsu.fpm.DAO.EstimationFunc_GroupDAO;
import edu.kubsu.fpm.DAO.GroupDAO;
import edu.kubsu.fpm.DAO.PersonDAO;
import edu.kubsu.fpm.entity.EstimationFunction;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * User: Marina
 * Date: 17.05.12
 * Time: 22:19
 */

@ManagedBean
@SessionScoped
public class FunctionToGroupBean {

    @EJB
    private EstimationFunc_GroupDAO func_groupDAO;
    
    @EJB
    private PersonDAO personDAO;

    @EJB
    private GroupDAO groupDAO;

    public String getFunctionForGroup(Integer groupId, Integer studentId){
        String function = "6 * x";
        if ((studentId > 0)&&(groupId > 0)){
            try{
                EstimationFunction estFunction = func_groupDAO.getFunctionByGroup(groupDAO.getGroupsById(groupId));
                function = estFunction.getFunction();
            } catch (Exception e){
                function = "";  // TODO Все будет зависеть от того, аналогом чего является ClassifierValue
            }
        }
        return function;
    }
}
