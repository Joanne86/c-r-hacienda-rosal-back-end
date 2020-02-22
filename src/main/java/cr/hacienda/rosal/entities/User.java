package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column (name = "document_number")
    private String documentNumber;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "second_name")
    private String secondName;
    @Column (name = "last_name")
    private String lastName;
    @Column
    private String cellphone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private UserType userType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_session", referencedColumnName = "id")
    private Session session;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
