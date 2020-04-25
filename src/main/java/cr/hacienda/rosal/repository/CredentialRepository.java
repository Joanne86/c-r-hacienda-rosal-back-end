package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Credential;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CredentialRepository extends CrudRepository<Credential, Integer> {
    @Query("SELECT c FROM Credential c WHERE c.user = ?1 AND c.password = ?2")
    Credential login (String user, String password);

}
