package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Debt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DebtRepository extends CrudRepository<Debt, Integer> {

    @Query("SELECT d FROM Debt d WHERE d.towerNumberHome=?1")
    Optional<Debt> findByTowerNumberHome(String towerNumberHome);
}
