package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.News;
import org.springframework.data.repository.CrudRepository;

public interface NewRepository extends CrudRepository<News, Integer> {

}
