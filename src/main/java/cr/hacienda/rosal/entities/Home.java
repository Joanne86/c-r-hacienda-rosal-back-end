package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name="home")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name="tower_number_home")
    private String towerNumberHome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_debt", referencedColumnName = "id")
    private Debt debt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTowerNumberHome() {
        return towerNumberHome;
    }

    public void setTowerNumberHome(String towerNumberHome) {
        this.towerNumberHome = towerNumberHome;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Debt getDebt() {
        return debt;
    }

    public void setDebt(Debt debt) {
        this.debt = debt;
    }
}

