package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.service.IHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements IHomeService {

    private Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    HomeRepository homeRepository;

    @Override
    public void saveAll(Iterable<Home> homes) {
        logger.info("Inicia guardado de todos los apartamentos");
        homeRepository.saveAll(homes);
    }

    @Override
    public Iterable<Home> getAllHomes() {
        logger.info("Inicia llamado de todos los apartamentos");
        return homeRepository.findAll();
    }
}
