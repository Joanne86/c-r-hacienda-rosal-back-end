package cr.hacienda.rosal.crhaciendarosalbackend.services;

import cr.hacienda.rosal.crhaciendarosalbackend.entities.ResidentCredentials;

import java.util.List;

public interface INotificationService {
    void addNumbersToGeneralMessage(List<ResidentCredentials> residentCredentialsList);
    void save(Iterable<ResidentCredentials> residentCredentialsList);
}
