package cr.hacienda.rosal.entities;

import javax.persistence.*;

@Entity
@Table(name="debt")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name="tower_number_home")
    private String towerNumberHome;
    @Column
    private float amount;
    @Column
    private int months;

    public String getTowerNumberHome() {
        return towerNumberHome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTowerNumberHome(String towerNumberHome) {
        this.towerNumberHome = towerNumberHome;
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

}
