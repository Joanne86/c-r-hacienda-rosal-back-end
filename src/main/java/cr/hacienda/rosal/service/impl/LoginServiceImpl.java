package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.entities.Session;
import cr.hacienda.rosal.service.ILoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {



    @Override
    public Session getSession(String username, String password) {
        return null;
    }
}
