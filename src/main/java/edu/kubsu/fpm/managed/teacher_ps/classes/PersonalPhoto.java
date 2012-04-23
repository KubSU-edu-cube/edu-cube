package edu.kubsu.fpm.managed.teacher_ps.classes;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 18.04.12
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
public class PersonalPhoto {
    private byte[] content;
    private int personId;

    public PersonalPhoto(byte[] content, int personId) {
        this.content = content;
        this.personId = personId;
    }

    public PersonalPhoto(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
