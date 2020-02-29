package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.service.ILoginService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    HomeRepository homeRepository;

    @Override
    public UserDto getSession(String username) throws Exception {
        Home home  = homeRepository.findByDocumentNumber(username);
        if(home != null){
            logger.info("Inicia mapeo de base de datos");
            return  MapperDtos.getUserDto(home);
        }else{
            logger.info("No se encontro registros con ese usuario");
            throw new Exception();
        }
    }
}
