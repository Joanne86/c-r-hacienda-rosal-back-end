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

    Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    ResidentRepository residentRepository;

    @Value("${arn.topic.all.residents}")
    private String awsTopicAllResidents;

    @Value("${arn.topic.debtors.residents}")
    private String awsTopicDebtors;

    @Value("${access.key}")
    private String accessKey;

    @Value("${secret.key}")
    private String secretKey;

    private static String ERROR_PUBLISH_MESSAGE = "Ocurrrio un error al enviar mensaje";
    private static String PREFIX_MESSAGE_TO_ALL = "Hacienda el Rosal te informa que ";

    private  SnsHandler snSHandler;

    @Override
    public void save(Iterable<ResidentCredentials> residentCredentialsList) {
        logger.info("Guardando en base de datos los numeros de telefono de los residentes");
        residentRepository.saveAll(residentCredentialsList);
    }

    @Override
    public void sendMessageToAll(String message) {
        setupSnSHandler();
        logger.info("Enviando un mensaje a todos los residentes del conjunto: {}", message);
        this.snSHandler.createPublishToTopic(awsTopicAllResidents, message);
    }

    /**
     * ARREGLAR MIRAR COMO ENVIAR A UNO SOLO
     * @param message
     */
    @Override
    public void sendMessageToOne(String message) {
        try {
            setupSnSHandler();
            this.snSHandler.createPublishToTopic(awsTopicAllResidents, message);
        } catch (Exception e) {
            logger.error(ERROR_PUBLISH_MESSAGE);
        }
    }

    @Override
    public void addAllNumbers(List<ResidentCredentials> residentCredentialsList){
        try {
            setupNotification(this.awsTopicAllResidents);
            setupSnSHandler();
            if(this.snSHandler != null){
                this.snSHandler.addNumbers(this.awsTopicAllResidents, residentCredentialsList);
            }
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de telefono de todo el conjunto");
        }
    }
    @Override
    public void addDebtorsNumbers(List<ResidentCredentials> residentCredentialsList){
        try {
            setupNotification(this.awsTopicDebtors);
            setupSnSHandler();
            this.snSHandler.addNumbers(this.awsTopicDebtors, residentCredentialsList);
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de los deudores");
        }
    }

    private void setupNotification(String arnTopic){
        logger.info("Trayendo datos de conexion...");
        this.awsTopicAllResidents = EncryptionUtil.decode(arnTopic);
        this.accessKey = EncryptionUtil.decode(accessKey);
        this.secretKey = EncryptionUtil.decode(secretKey);
    }

    private  void setupSnSHandler(){
        try {
            logger.info("Estableciendo conexion con topic");
            this.snSHandler = new SnsHandler(accessKey, secretKey);
        } catch (Exception e) {
            logger.error(ERROR_PUBLISH_MESSAGE);
        }
    }
}
