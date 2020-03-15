package cr.hacienda.rosal.service.impl;

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


@Service
public class DebtServiceImpl implements IDebtorService {
    private Logger logger = LoggerFactory.getLogger(DebtServiceImpl.class);
    @Autowired
    DebtRepository debtRepository;

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    IHomeService homeService;

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
        return MapperDtos.mapUserToUserDto(this.homeRepository.getAllDebtors());
    }

    @Override
    public Debt getDebtInfo() {
        return null;
    }
}
