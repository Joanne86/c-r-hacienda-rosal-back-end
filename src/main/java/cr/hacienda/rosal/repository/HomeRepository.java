package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Home;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HomeRepository extends CrudRepository<Home, Integer> {

    @Query("SELECT h from Home h Where h.user.userType = 1")
    Iterable<Home> findAllResidents();

    @Query("SELECT h from Home h WHERE h.debt.amount<>0 AND h.debt.months<>0 AND h.user.userType.id = 1")
    Iterable<Home> getAllDebtors();

    @Query("SELECT h.id FROM Home h WHERE h.towerNumberHome =?1")
    Integer getIdByTowerNumberHome(String towerNumberHome);

    @Query("SELECT h FROM Home h WHERE h.user.id =?1")
    Optional<Home> getHomeByIdUser(int idUser);
}
