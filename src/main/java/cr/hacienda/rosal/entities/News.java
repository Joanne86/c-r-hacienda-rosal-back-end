package cr.hacienda.rosal.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String information;
    @Column (name = "publish_date")
    private String publish;

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

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", information='" + information + '\'' +
                ", publish=" + publish +
                '}';
    }
}
