package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.repository.UserRepository;
import cr.hacienda.rosal.service.IUserService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    HomeRepository homeRepository;

    @Override
    public Iterable<UserDto> getAllUsers() {
        logger.info("Obteniendo usuarios de base de datos");
        return MapperDtos.mapUserToUserDto(homeRepository.findAll());
    }
}
