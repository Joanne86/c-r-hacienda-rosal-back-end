package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.repository.UserRepository;
import cr.hacienda.rosal.service.IUserService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<UserDto> getAllUsers() {
        logger.info("Obteniendo usuarios de base de datos");
        return MapperDtos.mapUserToUserDto(userRepository.findAll());
    }
}
