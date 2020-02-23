package cr.hacienda.rosal.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.*;
import cr.hacienda.rosal.entities.ResidentCredentials;
import cr.hacienda.rosal.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnsHandler {

    Logger logger = LoggerFactory.getLogger(SnsHandler.class);

    private AWSCredentials awsCredentials;
    private AmazonSNSClient snsClient;

    public SnsHandler(String accessKey, String secretKey) throws Exception {
        if (accessKey != null && !accessKey.equals("") && secretKey != null && !secretKey.equals("")) {
            this.awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        } else {
            throw new Exception("accessKey y secretKey no pueden ser nulos");
        }
    }

    public void createPublishToTopic(String topicArn, String msg) {
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        this.sendMessageToTopic(publishRequest);
    }

    private String sendMessageToTopic(PublishRequest publishRequest) {
        getAmazonSNSClient();
        PublishResult publishResult = this.snsClient.publish(publishRequest);
        return publishResult.getMessageId();
    }

    public void showNumbers(){
        getAmazonSNSClient();
        ListSubscriptionsResult result = this.snsClient.listSubscriptions();
        for (Subscription sub : result.getSubscriptions()) {
            logger.info(sub.getEndpoint());
        }
    }

    public void sendMessageToOne(String menssage, String phoneNumber){
        logger.info("Estableciendo parametros para mensaje de texto");
        getAmazonSNSClient();
        Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<>();
        sendSMSMessage(this.snsClient, menssage, phoneNumber, smsAttributes);
    }

    public void sendSMSMessage(AmazonSNSClient snsClient, String message,
                                      String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
        logger.info("Enviando mensaje al numero: {}",phoneNumber);
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        logger.info("Finaliza envio de mensaje exitoso: {}", result);
    }

    public void addNumbers(String awsTopic, ArrayList<String> cellphones){
        getAmazonSNSClient();
        logger.info("Agregando todos los numeros de telefono del conjunto");
        for (int i = 0; i<cellphones.size(); i++){
            logger.info("numero: {}", cellphones.get(i));
            subscribeToTopic(this.snsClient, awsTopic, "sms", cellphones.get(i));
        }
    }

    public void subscribeToTopic(AmazonSNSClient snsClient, String topicArn,
                                        String protocol, String endpoint) {
        SubscribeRequest subscribe = new SubscribeRequest(topicArn, protocol,
                endpoint);
        SubscribeResult subscribeResult = snsClient.subscribe(subscribe);
        logger.info("Subscribe request: {}",
                snsClient.getCachedResponseMetadata(subscribe));
        logger.info("Subscribe result: {}", subscribeResult);
    }

    private void getAmazonSNSClient(){
        if(this.snsClient == null){
            this.snsClient = new AmazonSNSClient(this.awsCredentials);
            snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        }
    }
}
