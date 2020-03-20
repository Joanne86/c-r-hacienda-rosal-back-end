package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.UserDto;

public interface ILoginService {

    UserDto getSession(String username) throws Exception;

}
