package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
}
