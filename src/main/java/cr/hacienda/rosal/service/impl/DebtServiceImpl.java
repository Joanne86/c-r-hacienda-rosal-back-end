package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.DebtDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Debt;
import cr.hacienda.rosal.repository.DebtRepository;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.service.IDebtorService;
import cr.hacienda.rosal.service.IHomeService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DebtServiceImpl implements IDebtorService {
    private Logger logger = LoggerFactory.getLogger(DebtServiceImpl.class);
    @Autowired
    DebtRepository debtRepository;

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    IHomeService homeService;

    @Autowired
    MapperDtos mapperDtos;

    @Override
    public void saveAll(Iterable<Debt> debtors) {
        debtRepository.saveAll(debtors);
    }

    @Override
    public void save(Debt debt) {
        debtRepository.save(debt);
    }

    @Override
    public Iterable<UserDto> getAllDebtors() {
        return mapperDtos.mapUserToUserDto(this.homeRepository.getAllDebtors());
    }

    @Override
    public DebtDto getDebtInfo(String towerNumberHome) {
        logger.info("Obteniendo deuda actual del residente");
        return mapperDtos.getDebt(debtRepository.findByTowerNumberHome(towerNumberHome));
    }

    @Override
    public void update(UserDto userDto) {
        Optional<Debt> debtReturn = debtRepository.findByTowerNumberHome(userDto.getTowerNumberHome());
        logger.info("Inicia actualizacion de debt en base de datos");
        if (debtReturn.isPresent()) {
            debtReturn.get().setTowerNumberHome(userDto.getTowerNumberHome());
            debtReturn.get().setAmount(userDto.getDebt());
            debtReturn.get().setMonths(userDto.getMonths());
            debtRepository.save(debtReturn.get());
        }
    }
}
