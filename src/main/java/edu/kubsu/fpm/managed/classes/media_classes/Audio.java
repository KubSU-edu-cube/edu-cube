package edu.kubsu.fpm.managed.classes.media_classes;

/**
 * Created with IntelliJ IDEA.
 * User: anna
 * Date: 30.05.12
 * Time: 18:17
 * To change this template use File | Settings | File Templates.
 */
public class Audio {
    private int id;
    private byte[] content = new byte[10485760];

    public Audio(int id, byte[] content) {
        this.id = id;
        this.content = content;
    }

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
