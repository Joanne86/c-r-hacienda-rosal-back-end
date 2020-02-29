package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.service.ILoginService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    HomeRepository homeRepository;

    @Override
    public UserDto getSession(String username) {
       return MapperDtos.getUserDto(homeRepository.findByDocumentNumber(username));
    }
}
