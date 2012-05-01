package edu.kubsu.fpm.managed.teacher_ps.classes;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 27.04.12
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class CourseLection {

    private int id;
    private byte[] content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
