package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Home;

public interface IHomeService {
    Iterable<UserDto> getAllUsers();
    void saveAll(Iterable<Home> homes);
    void save(Home home);
    void update(UserDto userDto);
}
