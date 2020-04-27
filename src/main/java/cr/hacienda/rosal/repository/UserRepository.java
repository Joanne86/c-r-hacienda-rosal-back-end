package cr.hacienda.rosal.repository;
import cr.hacienda.rosal.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.documentNumber=?1")
    Optional<User> findByDocumentNumber(String documentNumber);

    @Query("SELECT u.id FROM User u WHERE u.documentNumber =?1")
    int getIdByDocumentNumber(String documentNumber);
}
