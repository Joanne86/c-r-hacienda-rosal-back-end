package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name="debt")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private float amount;
    @Column
    private int months;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tower_number_home", referencedColumnName = "tower_number_home")
    private Home home;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
