package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.ResidentCredentials;
import org.springframework.data.repository.CrudRepository;

public interface ResidentRepository extends CrudRepository<ResidentCredentials, Integer> {


}
