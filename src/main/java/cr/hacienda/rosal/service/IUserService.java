package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.UserDto;

public interface IUserService {
    Iterable<UserDto> getAllUsers();
}
