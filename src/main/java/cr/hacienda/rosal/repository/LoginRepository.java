package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<User, Integer> {

}
