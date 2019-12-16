package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.MessageDto;
import cr.hacienda.rosal.entities.ResidentCredentials;

import java.util.List;

public interface INotificationService {
    void save(Iterable<ResidentCredentials> residentCredentialsList);
    void sendMessageToAll(String message);
    void sendMessageToOne(MessageDto messageDto);
    void addAllNumbers(List<ResidentCredentials> residentCredentialsList);
    void addDebtorsNumbers(List<ResidentCredentials> residentCredentialsList);
    void getAllNumber();
}
