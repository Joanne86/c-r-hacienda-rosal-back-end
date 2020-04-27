package cr.hacienda.rosal.repository;

import cr.hacienda.rosal.entities.Credential;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialRepository extends CrudRepository<Credential, Integer> {
    @Query("SELECT c FROM Credential c WHERE c.user = ?1 AND c.password = ?2")
    Credential login (String user, String password);

    @Query("SELECT c.user FROM Credential c")
    Iterable<String> getUserNames();

    @Query("SELECT c.home.user.documentNumber FROM Credential c")
    Iterable<String> getDocumentNumbers();

    @Query("SELECT c FROM Credential c WHERE c.id=?1")
    Optional<Credential> getById(int id);
}
