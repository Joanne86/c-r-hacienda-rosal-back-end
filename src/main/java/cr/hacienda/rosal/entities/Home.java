package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name="home")
public class Home {
    @Id
    @Column(name="tower_number_home")
    private String towerNumberHome;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document", referencedColumnName = "document_number")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_debt", referencedColumnName = "id")
    private Debt debt;


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
