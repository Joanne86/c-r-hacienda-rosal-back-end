package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.MessageDto;
import cr.hacienda.rosal.entities.ResidentCredentials;
import cr.hacienda.rosal.entities.User;

import java.util.List;

public interface INotificationService {
    void save(Iterable<User> residentCredentialsList);
    void sendMessageToAll(String message);
    void sendMessageToOne(MessageDto messageDto);
    void addAllNumbers(List<User> residentCredentialsList);
    void addDebtorsNumbers(List<User> residentCredentialsList);
    void getAllNumber();
}
