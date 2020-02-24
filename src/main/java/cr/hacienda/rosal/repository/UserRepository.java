package cr.hacienda.rosal.repository;
import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


    /*SELECT tower_number_home, user.document_number, first_name, second_name, last_name, cellphone,
    id_type FROM bd_hacienda_rosal.home join bd_hacienda_rosal.user on home.document_number =
    user.document_number;*/
}
