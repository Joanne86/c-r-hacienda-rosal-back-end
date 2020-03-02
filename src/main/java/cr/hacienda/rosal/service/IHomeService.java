package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Home;

public interface IHomeService {
    void saveAll(Iterable<Home> users);
    Iterable<UserDto> getAllUsers();
}
