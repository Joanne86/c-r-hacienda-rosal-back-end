package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.entities.ResidentCredentials;
import cr.hacienda.rosal.repository.ResidentRepository;
import cr.hacienda.rosal.service.INotificationService;
import cr.hacienda.rosal.utils.EncryptionUtil;
import cr.hacienda.rosal.utils.SnsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    ResidentRepository residentRepository;


    @Value("${arn.topic.all.residents}")
    private String awsTopicAllResidents;

    @Value("${arn.topic.debtors.residents}")
    private String getAwsTopicDebtors;

    @Value("${access.key}")
    private String accessKey;

    @Value("${secret.key}")
    private String secretKey;

    private static String ERROR_PUBLISH_MESSAGE = "ocurrrio un error al enviar mensaje";

    Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void addNumbersToGeneralMessage(List<ResidentCredentials> residentCredentialsList) {

    }

    @Override
    public void save(Iterable<ResidentCredentials> residentCredentialsList) {
        //residentRepository.saveAll(residentCredentialsList);
    }

    public void initNotification(){
        this.awsTopicAllResidents = EncryptionUtil.decode(awsTopicAllResidents);
        this.accessKey = EncryptionUtil.decode(accessKey);
        this.secretKey = EncryptionUtil.decode(secretKey);
    }

    @Override
    public void sendMessageToAll(String message) {
        try {
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.createPublishToTopic(awsTopicAllResidents, message);
        } catch (Exception e) {
            logger.error(ERROR_PUBLISH_MESSAGE);
        }
    }

    @Override
    public void sendMessageToOne(String message) {
        try {
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.createPublishToTopic(awsTopicAllResidents, message);
        } catch (Exception e) {
            logger.error(ERROR_PUBLISH_MESSAGE);
        }
    }

    @Override
    public void addAllNumbers(List<ResidentCredentials> residentCredentialsList){
        try {
            initNotification();
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.addNumbers(awsTopicAllResidents, residentCredentialsList);
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de todo el conjunto");
        }
    }
    @Override
    public void addDebtorsNumbers(List<ResidentCredentials> residentCredentialsList){
        try {
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.addNumbers(getAwsTopicDebtors, residentCredentialsList);
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de los deudores");
        }
    }
}
