package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.MarkDAO;
import edu.kubsu.fpm.entity.StudentAnswer;
import edu.kubsu.fpm.model.Mark;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * User: Marina
 * Date: 25.05.12
 * Time: 18:02
 */

@ManagedBean
@SessionScoped
public class CheckTaskBean {

    private StudentAnswer answer;
    private Double studentMark;

    @EJB
    private MarkDAO markDAO;

    public CheckTaskBean() {
        answer = (StudentAnswer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("studentAnswer");
    }

    public String saveMark(){
        Mark mark = new Mark();
        mark.setMark(studentMark);
        mark.setStudent(answer.getPerson());
        mark.setLection(answer.getTask().getTest().getLection());
        mark.setTest(answer.getTask().getTest());
        markDAO.persist(mark);
        return "check_creative_tasks";
    }

    public Double getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(Double studentMark) {
        this.studentMark = studentMark;
    }

    public StudentAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(StudentAnswer answer) {
        this.answer = answer;
    }
}
