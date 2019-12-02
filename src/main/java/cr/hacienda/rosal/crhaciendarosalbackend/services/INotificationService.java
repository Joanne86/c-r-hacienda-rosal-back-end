package cr.hacienda.rosal.crhaciendarosalbackend.services;

import cr.hacienda.rosal.crhaciendarosalbackend.entities.ResidentCredentials;

import java.util.List;

public interface INotificationService {
    void addNumbersToGeneralMessage(List<ResidentCredentials> residentCredentialsList);
    void save(Iterable<ResidentCredentials> residentCredentialsList);
    void sendMessageToAll(String message);
    void sendMessageToOne(String message);
    void addAllNumbers(List<ResidentCredentials> residentCredentialsList);
    void addDebtorsNumbers(List<ResidentCredentials> residentCredentialsList);
}
