package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column (name = "document_number")
    private String documentNumber;
    @Column
    private String name;
    @Column
    private String cellphone;

    @ManyToOne(cascade = {CascadeType.ALL})
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
}
