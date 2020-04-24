package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface NewRepository extends CrudRepository<News, Integer> {
    @Query("SELECT n FROM News n WHERE n.id=?1")
    Optional<News> findById(int id);

}
