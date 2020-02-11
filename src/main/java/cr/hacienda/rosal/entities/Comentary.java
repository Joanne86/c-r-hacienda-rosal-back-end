package cr.hacienda.rosal.entities;

import java.util.Date;

public class Comentary {
    private int id;
    private String message;
    private Date publishDate;
    //document
    //idNews


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
