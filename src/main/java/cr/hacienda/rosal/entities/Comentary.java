package cr.hacienda.rosal.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comentary")
public class Comentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String message;
    @Column (name="publish_date")
    private Date publishDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document", referencedColumnName = "document_number" )
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_news", referencedColumnName = "id" )
    private News news;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
