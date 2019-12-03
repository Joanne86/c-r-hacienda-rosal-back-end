package cr.hacienda.rosal.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.*;
import cr.hacienda.rosal.entities.ResidentCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SnsHandler {

    Logger logger = LoggerFactory.getLogger(SnsHandler.class);

    AWSCredentials awsCredentials;

    public SnsHandler(String accessKey, String secretKey) throws Exception {
        if (accessKey != null && !accessKey.equals("") && secretKey != null && !secretKey.equals("")) {
            this.awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        } else {
            throw new Exception("accessKey y secretKey no pueden ser nulos");
        }
    }

    /**
     * Metodo que envia un mensaje igual a todos los residentes del conjunto
     * @param topicArn
     * @param msg
     */
    public void createPublishToTopic(String topicArn, String msg) {
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        this.sendTopic(publishRequest);
    }

    private String sendTopic(PublishRequest publishRequest) {
        AmazonSNSClient snsClient = new AmazonSNSClient(this.awsCredentials);
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        PublishResult publishResult = snsClient.publish(publishRequest);
        return publishResult.getMessageId();

    }

    public void showNumbers(){
        AmazonSNSClient snsClient = new AmazonSNSClient(this.awsCredentials);
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        ListSubscriptionsResult result = snsClient.listSubscriptions();
        for (Subscription sub : result.getSubscriptions()) {
            logger.info(sub.getEndpoint());
        }
    }

    public void addNumbers(String awsTopic, List<ResidentCredentials> residentCredentialsList){
        AmazonSNSClient snsClient = new AmazonSNSClient(this.awsCredentials);
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        for (ResidentCredentials listNumbers: residentCredentialsList){
            subscribeToTopic(snsClient, awsTopic, "sms", listNumbers.getNumberCellphone());
        }
    }

    public static void subscribeToTopic(AmazonSNSClient snsClient, String topicArn,
                                        String protocol, String endpoint) {
        SubscribeRequest subscribe = new SubscribeRequest(topicArn, protocol,
                endpoint);
        SubscribeResult subscribeResult = snsClient.subscribe(subscribe);
        System.out.println("Subscribe request: " +
                snsClient.getCachedResponseMetadata(subscribe));
        System.out.println("Subscribe result: " + subscribeResult);

    }

}
