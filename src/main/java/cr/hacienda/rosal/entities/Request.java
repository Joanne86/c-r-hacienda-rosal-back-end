package cr.hacienda.rosal.entities;

import javax.persistence.*;

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
    private String publishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    private StateRequest stateRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private TypeRequest typeRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_home", referencedColumnName = "id")
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
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
