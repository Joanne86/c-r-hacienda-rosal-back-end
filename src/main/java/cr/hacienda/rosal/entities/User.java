package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column (name = "document_number")
    private String documentNumber;
    @Column
    private String name;
    @Column
    private String cellphone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private UserType userType;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", name='" + name + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", userType=" + userType +
                '}';
    }
}
