package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Session;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Session, Integer> {
}
