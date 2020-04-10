package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    @Query("SELECT r FROM Request r WHERE r.id= ?1")
    Optional<Request> findAllById(int id);

    @Query("SELECT r FROM Request r WHERE r.home.towerNumberHome = ?1")
    Iterable<Request> findAllByTowerNumberHome(String towerNumberHome);
}
