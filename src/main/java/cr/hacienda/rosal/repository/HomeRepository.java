package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Home;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HomeRepository extends CrudRepository<Home, Integer> {
    @Query("SELECT h from Home h Where h.user.userType = 1")
    Iterable<Home> findAllResidents();

    @Query("SELECT h from Home h WHERE h.user.documentNumber = ?1")
    Home findByDocumentNumber(String userName);
}
