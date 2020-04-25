package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.CredentialDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Credential;

import java.util.ArrayList;

public interface ILoginService {

    UserDto getSession(CredentialDto credentialDto) throws Exception;
    void save(Credential credential);
    void saveAll(ArrayList<Credential> credentials);

}
