package cr.hacienda.rosal.crhaciendarosalbackend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "resident_credentials")
public class ResidentCredentials {

    private int id;
    @Column
    private String name;
    @Column (name = "number_cellphone")
    private String numberCellphone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberCellphone() {
        return numberCellphone;
    }

    public void setNumberCellphone(String numberCellphone) {
        this.numberCellphone = numberCellphone;
    }
}
