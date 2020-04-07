package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Commentary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends CrudRepository<Commentary, Integer> {

    @Query("SELECT c FROM Commentary c WHERE c.news.id = ?1")
    Iterable<Commentary> findAllByIdNews(int idNews);
}
