package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Home;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends CrudRepository<Home, Integer> {
}
