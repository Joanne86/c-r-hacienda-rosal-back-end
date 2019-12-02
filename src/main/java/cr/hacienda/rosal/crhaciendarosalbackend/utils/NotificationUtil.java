package cr.hacienda.rosal.crhaciendarosalbackend.utils;

import cr.hacienda.rosal.crhaciendarosalbackend.entities.ResidentCredentials;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;

import java.util.List;

public class NotificationUtil {


    @Value("${arn.topic.all.residents}")
    private String awsTopicAllResidents;

    @Value("${arn.topic.debtors.residents}")
    private String getAwsTopicDebtors;

    @Value("${access.key}")
    private String accessKey;

    @Value("${secret.key}")
    private String secretKey;

    private static String ERROR_PUBLISH_MESSAGE = "ocurrrio un error al enviar mensaje";

    Logger logger = LoggerFactory.getLogger(NotificationUtil.class);

    public NotificationUtil(){
        this.awsTopicAllResidents = EncryptionUtil.decode(awsTopicAllResidents);
        this.accessKey = EncryptionUtil.decode(accessKey);
        this.secretKey = EncryptionUtil.decode(secretKey);
    }

    public void sendMessageToAll(String message) {
        try {
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.createPublishToTopic(awsTopicAllResidents, message);
        } catch (Exception e) {
            logger.error(ERROR_PUBLISH_MESSAGE);
        }
    }

    public void sendMessageToOne(String message) {
        try {
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.createPublishToTopic(awsTopicAllResidents, message);
        } catch (Exception e) {
            logger.error(ERROR_PUBLISH_MESSAGE);
        }
    }


    public void addAllNumbers(List<ResidentCredentials> residentCredentialsList){
        try {
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.addNumbers(awsTopicAllResidents, residentCredentialsList);
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de todo el conjunto");
        }
    }

    public void addDebtorsNumbers(List<ResidentCredentials> residentCredentialsList){
        try {
            SnsHandler bdbSnSHandler = new SnsHandler(accessKey, secretKey);
            bdbSnSHandler.addNumbers(getAwsTopicDebtors, residentCredentialsList);
        } catch (Exception e) {
            logger.error("ocurrio un error al agregar los numeros de los deudores");
        }
    }

    public void setGetAwsTopicDebtors(String getAwsTopicDebtors) {
        this.getAwsTopicDebtors = getAwsTopicDebtors;
    }


    public void setAwsTopicAllResidents(String awsTopicAllResidents) {
        this.awsTopicAllResidents = awsTopicAllResidents;
    }


    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }


    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
