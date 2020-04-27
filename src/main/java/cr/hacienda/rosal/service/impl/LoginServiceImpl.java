package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.CredentialDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Credential;
import cr.hacienda.rosal.entities.Debt;
import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.entities.User;
import cr.hacienda.rosal.repository.CredentialRepository;
import cr.hacienda.rosal.repository.DebtRepository;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.repository.UserRepository;
import cr.hacienda.rosal.service.ILoginService;
import cr.hacienda.rosal.utils.MapperDtos;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService {
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    MapperDtos mapperDtos;

    @Override
    public UserDto getSession(CredentialDto credentialDto) throws NotFoundException {

        Credential credential = credentialRepository.login(credentialDto.getUser(), credentialDto.getPassword());
        if(credential != null){
            UserDto userDto = mapperDtos.getUserDtoOfCredential(credential);
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


    @Override
    public Boolean validateDocument(String documentNumber) {
        ArrayList<String> documents = (ArrayList<String>) credentialRepository.getDocumentNumbers();
        return documents.contains(documentNumber);
    }

    @Override
    public Boolean validateUserName(String user) {
        ArrayList<String> userNames = (ArrayList<String>) credentialRepository.getUserNames();
        return userNames.contains(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        Optional<Credential> credential = credentialRepository.getById(userDto.getIdSession());
        if(credential.isPresent()){
            if(!userDto.getPassword().isEmpty()){
                credential.get().setPassword(userDto.getPassword());
            }
            credential.get().setUser(userDto.getUser());
            credential.get().getHome().getUser().setName(userDto.getName());
            credential.get().getHome().getUser().setDocumentNumber(userDto.getDocumentNumber());
            logger.info("Actualizando info");
            credentialRepository.save(credential.get());
        }
    }
}
