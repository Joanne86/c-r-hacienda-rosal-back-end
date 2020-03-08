package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Debt;
import cr.hacienda.rosal.repository.DebtRepository;
import cr.hacienda.rosal.service.IDebtorService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtServiceImpl implements IDebtorService {

    @Autowired
    DebtRepository debtRepository;

    @Override
    public void saveAll(Iterable<Debt> debtors) {
        debtRepository.saveAll(debtors);
    }

    @Override
    public Iterable<UserDto> getAllDebtors() {
        return MapperDtos.mapDebtToUserDto(this.debtRepository.getAllDebtors());
    }

    @Override
    public Debt getDebtInfo() {
        return null;
    }
}
