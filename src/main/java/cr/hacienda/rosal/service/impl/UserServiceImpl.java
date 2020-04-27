package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.User;
import cr.hacienda.rosal.repository.UserRepository;
import cr.hacienda.rosal.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveAll(Iterable<User> users) {
        logger.info("Guardando en base de datos los residentes");
        userRepository.saveAll(users);
    }

    @Override
    public void save(User user) {

        logger.info("Guardando en base de datos del residente: {}", user.toString());
        userRepository.save(user);
    }

    @Override
    public void update(UserDto userDto) {
        Optional<User> user = userRepository.findByDocumentNumber(userDto.getDocumentNumber());
        if (user.isPresent()) {
            user.get().setCellphone(userDto.getCellphone());
            userRepository.save(user.get());
        }
    }
}
