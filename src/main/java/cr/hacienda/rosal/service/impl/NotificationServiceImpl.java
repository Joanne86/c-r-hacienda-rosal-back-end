package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.MessageDto;
import cr.hacienda.rosal.entities.User;
import cr.hacienda.rosal.repository.UserRepository;
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
    UserRepository userRepository;

    @Value("${arn.topic.all.residents}")
    private String awsTopicAllResidents;

    private String awsTopicAllResidentsDec;

    @Value("${arn.topic.debtors.residents}")
    private String awsTopicDebtors;

    @Value("${access.key}")
    private String accessKey;

    private String accessKeyDec;

    @Value("${secret.key}")
    private String secretKey;

    private String secretKeyDec;

    private static String ERROR_PUBLISH_MESSAGE = "Ocurrrio un error al enviar mensaje";
    private static String PREFIX_MESSAGE_TO_ALL = "Hacienda el Rosal te informa que ";

    private  SnsHandler snSHandler;

    @Override
    public void save(Iterable<User> residentCredentialsList) {
        logger.info("Guardando en base de datos los numeros de telefono de los residentes");
        userRepository.saveAll(residentCredentialsList);
    }

    @Override
    public void sendMessageToAll(String message) {
        setDataToConnectSNS();
        logger.info("Enviando un mensaje a todos los residentes del conjunto: {}", message);
        this.snSHandler.createPublishToTopic(awsTopicAllResidentsDec, message);
    }

    @Override
    public void sendMessageToOne(MessageDto messageDto) {
        try {
            setDataToConnectSNS();
            this.snSHandler.sendMessageToOne(messageDto.getMessage(), messageDto.getPhoneNumber());
        } catch (Exception e) {
            logger.error("error: {} message: {}", ERROR_PUBLISH_MESSAGE, e.getMessage());
        }
    }

    @Override
    public void addAllNumbers(List<User> residentCredentialsList){
        try {
            setDataToConnectSNS();
            if(this.snSHandler != null){
                this.snSHandler.addNumbers(this.awsTopicAllResidentsDec, residentCredentialsList);
            }
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de telefono de todo el conjunto");
        }
    }
    @Override
    public void addDebtorsNumbers(List<User> residentCredentialsList){
        try {
            setDataToConnectSNS();
            this.snSHandler.addNumbers(this.awsTopicDebtors, residentCredentialsList);
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de los deudores");
        }
    }

    @Override
    public void getAllNumber() {
        try {
            setDataToConnectSNS();
            this.snSHandler.showNumbers();
        } catch (Exception e) {
            logger.error("Ocurrio un error al obtener todos los numeros del conjunto desde aws");
        }
    }

    private void setDataToConnectSNS(){
        setupNotification(this.awsTopicAllResidents);
        setupSnSHandler();
    }

    private void setupNotification(String arnTopic){
        logger.info("Trayendo datos de conexion...");
        this.awsTopicAllResidentsDec = EncryptionUtil.decode(arnTopic);
        this.accessKeyDec = EncryptionUtil.decode(this.accessKey);
        this.secretKeyDec = EncryptionUtil.decode(this.secretKey);
    }

    private  void setupSnSHandler(){
        try {
            logger.info("Estableciendo conexion con topic...");
            this.snSHandler = new SnsHandler(accessKeyDec, secretKeyDec);
        } catch (Exception e) {
            logger.error(ERROR_PUBLISH_MESSAGE);
        }
    }
}
