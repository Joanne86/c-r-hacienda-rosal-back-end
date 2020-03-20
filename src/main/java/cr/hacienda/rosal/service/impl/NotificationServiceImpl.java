package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.MessageDto;
import cr.hacienda.rosal.repository.UserRepository;
import cr.hacienda.rosal.service.INotificationService;
import cr.hacienda.rosal.utils.EncryptionUtil;
import cr.hacienda.rosal.utils.SnsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;

@Service
public class NotificationServiceImpl implements INotificationService {

    private Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Value("${arn.topic.all.residents}")
    private String awsTopicAllResidents;

    private String awsTopicAllResidentsDec;

    @Value("${arn.topic.debtors.residents}")
    private String awsTopicDebtors;

    private String awsTopicDebtorsDec;

    @Value("${access.key}")
    private String accessKey;

    private String accessKeyDec;

    @Value("${secret.key}")
    private String secretKey;

    private String secretKeyDec;

    private static String ERROR_PUBLISH_MESSAGE = "Ocurrio un error al enviar mensaje";
    private static String PREFIX_MESSAGE_TO_ALL = "Hacienda el Rosal te informa que ";

    private  SnsHandler snSHandler;



    @Override
    public void sendMessageToAllResidents(String message) {
        setDataToConnectSNSToAllResidents();
        String messageToSend=addPrefix(message);
        logger.info("Enviando un mensaje a todos los residentes del conjunto: {}", messageToSend);
        this.snSHandler.createPublishToTopic(awsTopicAllResidentsDec, messageToSend);
    }

    @Override
    public void sendMessageToAllDebtors(String message) {
        setDataToConnectSNSToAllDebtors();
        String messageToSend=addPrefix(message);
        logger.info("Enviando un mensaje a todos los residentes deudores del conjunto: {}", messageToSend);
        this.snSHandler.createPublishToTopic(awsTopicDebtorsDec, messageToSend);
    }

    @Override
    public void sendMessageToOne(MessageDto messageDto) {
        try {
            setDataToConnectSNSToAllResidents();
            String messageToSend=addPrefix(messageDto.getMessage());
            this.snSHandler.sendMessageToOne(messageToSend, messageDto.getPhoneNumber());
        } catch (Exception e) {
            logger.error("error: {} message: {}", ERROR_PUBLISH_MESSAGE, e.getMessage());
        }
    }

    @Override
    public void addAllNumbers(ArrayList<String> cellphones){
        try {
            setDataToConnectSNSToAllResidents();
            if(this.snSHandler != null){
                this.snSHandler.addNumbers(this.awsTopicAllResidentsDec, cellphones);
            }
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de telefono de todo el conjunto");
        }
    }

    @Override
    public void addNumber(String cellphone) {
        try {
            setDataToConnectSNSToAllResidents();
            if(this.snSHandler != null){
                this.snSHandler.addNumber(this.awsTopicAllResidentsDec, cellphone);
            }
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de telefono de todo el conjunto");
        }
    }

    @Override
    public void addDebtorsNumber(ArrayList<String> cellphones){
        try {
            setDataToConnectSNSToAllDebtors();
            this.snSHandler.addNumbers(this.awsTopicDebtorsDec, cellphones);
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de los deudores");
        }
    }

    @Override
    public void getAllNumber() {
        try {
            setDataToConnectSNSToAllResidents();
            this.snSHandler.showNumbers();
        } catch (Exception e) {
            logger.error("Ocurrio un error al obtener todos los numeros del conjunto desde aws");
        }
    }
    @Override
    public void deleteNumber(String cellphone) {
        try {
            setDataToConnectSNSToAllResidents();
            this.snSHandler.deleteSubcriptor(cellphone);
        } catch (Exception e) {
            logger.error("Ocurrio al eliminar numero de celular de aws");
        }
    }
    @Override
    public void getAllDebtorsNumber() {
        try {
            setDataToConnectSNSToAllDebtors();
            this.snSHandler.showNumbers();
        } catch (Exception e) {
            logger.error("Ocurrio un error al obtener todos los numeros del conjunto desde aws");
        }
    }

    private void setDataToConnectSNSToAllDebtors(){
        setupNotificationToAllDebtors(this.awsTopicDebtors);
        setupSnSHandler();
    }

    private void setupNotificationToAllDebtors(String arnTopic){
        logger.info("Trayendo datos de conexion...");
        this.awsTopicDebtorsDec = EncryptionUtil.decode(arnTopic);
        decAccess();
    }

    private void setDataToConnectSNSToAllResidents(){
        setupNotificationToAllResidents(this.awsTopicAllResidents);
        setupSnSHandler();
    }

    private void setupNotificationToAllResidents(String arnTopic){
        logger.info("Trayendo datos de conexion...");
        this.awsTopicAllResidentsDec = EncryptionUtil.decode(arnTopic);
        decAccess();
    }

    private void decAccess(){
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

    private String addPrefix(String message){
        return PREFIX_MESSAGE_TO_ALL.concat(message);
    }
}
