package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.CredentialDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Credential;
import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.repository.CredentialRepository;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.service.ILoginService;
import cr.hacienda.rosal.utils.MapperDtos;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginServiceImpl implements ILoginService {
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    CredentialRepository credentialRepository;

    @Override
    public UserDto getSession(CredentialDto credentialDto) throws NotFoundException {

        Credential credential = credentialRepository.login(credentialDto.getUser(), credentialDto.getPassword());
        if(credential != null){
            UserDto userDto = MapperDtos.getUserDtoOfCredential(credential);
            logger.info("Inicia mapeo de base de datos");
            return  userDto;
        }else{
            logger.info("No se encontro registros con ese usuario");
            throw new NotFoundException("NO_USER_REGISTRARED");
        }
    }

    @Override
    public void save(Credential credential) {
        credentialRepository.save(credential);
    }

    @Override
    public void saveAll(ArrayList<Credential> credentials) {
        credentialRepository.saveAll(credentials);
    }

}
