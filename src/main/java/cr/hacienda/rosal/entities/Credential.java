package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name= "credential")
public class Credential {

    @Id
    @Column
    private String user;
    @Column
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tower_number_home_c", referencedColumnName = "tower_number_home")
    private Home home;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
