package cr.hacienda.rosal.entities;

public class Request {

    private int id;
    private String message;
    //id_state int (2) not null,
   // id_type int (2) not null,
    //tower_number_home varchar (15) not null,


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
}
