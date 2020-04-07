package cr.hacienda.rosal.dto;

public class NewsDto {
    private int id;
    private String information;
    private String publish;
    private int commentaries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(int commentaries) {
        this.commentaries = commentaries;
    }
}
