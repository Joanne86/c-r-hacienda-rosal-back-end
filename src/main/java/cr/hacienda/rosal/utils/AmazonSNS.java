package cr.hacienda.rosal.utils;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;

public final class AmazonSNS {

    private AmazonSNS amazonSNS;
    private AmazonSNSClient snsClient;


    private AmazonSNS() {

    }

    public AmazonSNS getAmazonSNSInstance() {
        if (this.amazonSNS == null) {
            return this.amazonSNS = new AmazonSNS();
        }
        return this.amazonSNS;
    }


}
