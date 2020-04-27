package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @Column
    private int id;
    @Column
    private String rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", rol='" + rol + '\'' +
                '}';
    }
}
