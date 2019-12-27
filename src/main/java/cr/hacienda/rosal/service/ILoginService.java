package cr.hacienda.rosal.service;

import cr.hacienda.rosal.entities.Session;

public interface ILoginService {
    Session getSession(String username, String password);

}
