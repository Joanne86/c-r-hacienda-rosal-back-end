package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.User;

public interface IUserService {

    void saveAll(Iterable<User> users);
    void save(User user);
    void update(UserDto userDto);


}
