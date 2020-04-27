package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.DebtDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Debt;

public interface IDebtorService {
    void saveAll(Iterable<Debt> debtors);
    void save(Debt debt);
    Iterable<UserDto> getAllDebtors();
    DebtDto getDebtInfo(String towerNumberHome);
    void update(UserDto userDto);
}
