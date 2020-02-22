package cr.hacienda.rosal.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String message;
    @Column
    private String response;
    @Column(name = "publish_date")
    private Date publishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    private StateRequest stateRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private TypeRequest typeRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tower_number_home", referencedColumnName = "tower_number_home")
    private Home home;

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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public StateRequest getStateRequest() {
        return stateRequest;
    }

    public void setStateRequest(StateRequest stateRequest) {
        this.stateRequest = stateRequest;
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(TypeRequest typeRequest) {
        this.typeRequest = typeRequest;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
