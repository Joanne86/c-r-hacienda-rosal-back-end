package cr.hacienda.rosal.crhaciendarosalbackend.services.impl;

import cr.hacienda.rosal.crhaciendarosalbackend.entities.ResidentCredentials;
import cr.hacienda.rosal.crhaciendarosalbackend.repositories.ResidentRepository;
import cr.hacienda.rosal.crhaciendarosalbackend.services.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    ResidentRepository residentRepository;

    @Override
    public void addNumbersToGeneralMessage(List<ResidentCredentials> residentCredentialsList) {

    }

    @Override
    public void save(Iterable<ResidentCredentials> residentCredentialsList) {
        //residentRepository.saveAll(residentCredentialsList);
    }
}
