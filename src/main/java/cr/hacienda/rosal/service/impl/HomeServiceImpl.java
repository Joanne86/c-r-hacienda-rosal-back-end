package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.service.IHomeService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeServiceImpl implements IHomeService {

    private Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    HomeRepository homeRepository;
    @Autowired
    MapperDtos mapperDtos;

    @Override
    public Iterable<UserDto> getAllUsers() {
        logger.info("Obteniendo usuarios de base de datos");
        return mapperDtos.mapUserToUserDto(homeRepository.findAllResidents());
    }

    @Override
    public void saveAll(Iterable<Home> homes) {
        logger.info("Inicia guardado de todos los apartamentos");
        homeRepository.saveAll(homes);
    }

    @Override
    public void save(Home home) {
        logger.info("Inicia guardado del aparamento");
        homeRepository.save(home);
    }

    @Override
    public void update(UserDto userDto) {
        Optional<Home> home  =homeRepository.getHomeByIdUser(userDto.getIdUser());
        if(home.isPresent()){
            home.get().setTowerNumberHome(userDto.getTowerNumberHome());
            homeRepository.save(home.get());
        }
    }
}
