package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


}
