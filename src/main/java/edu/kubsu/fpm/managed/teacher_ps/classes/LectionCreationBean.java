package edu.kubsu.fpm.managed.teacher_ps.classes;

import edu.kubsu.fpm.DAO.LectionDAO;
import edu.kubsu.fpm.entity.Course_variation;
import edu.kubsu.fpm.entity.Lection;
import edu.kubsu.fpm.entity.Person;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 27.04.12
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class LectionCreationBean {

    private String courseName;
    private String lectionName;
    private String shortLectionContent;
    private UploadedFile uploadedFile;
    private Date lectionDate;
    private int lectionId;

    @EJB
    LectionDAO lectionDAO;

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Успешно загружен файл "+ event.getFile().getFileName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.setUploadedFile(event.getFile());
    }
    public void saveLection(){
        Person person =
                (Person) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("person");
        List<Course_variation> variationList = new ArrayList<Course_variation>();
        variationList.add(
                (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("courseVariation")
        );


        Lection lection = new Lection();
        lection.setAuthor(person);
        lection.setVariationList(variationList);
        lection.setContent(uploadedFile.getContents());
        lection.setDescription(shortLectionContent);
        lection.setLection(null);
        lection.setName(this.lectionName);

        lectionDAO.persist(lection);

        this.setLectionId(lectionDAO.getLectionByName(lectionName).getId());
    }
    public String getCourseName() {
        Course_variation course_variation =
                (Course_variation) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("courseVariation");
        return course_variation.getCourse().getName();
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLectionName() {
        return lectionName;
    }

    public void setLectionName(String lectionName) {
        this.lectionName = lectionName;
    }

    public String getShortLectionContent() {
        return shortLectionContent;
    }

    public void setShortLectionContent(String shortLectionContent) {
        this.shortLectionContent = shortLectionContent;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Date getLectionDate() {
        return lectionDate;
    }

    public void setLectionDate(Date lectionDate) {
        this.lectionDate = lectionDate;
    }

    public int getLectionId() {
        return lectionId;
    }

    public void setLectionId(int lectionId) {
        this.lectionId = lectionId;
    }
}
