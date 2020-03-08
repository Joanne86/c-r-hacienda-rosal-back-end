package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Debt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DebtRepository extends CrudRepository<Debt, Integer> {
    @Query("SELECT d FROM Debt d WHERE d.amount<>0 AND d.months<>0 AND d.home.user.userType.id = 1")
    Iterable<Debt> getAllDebtors();
}
