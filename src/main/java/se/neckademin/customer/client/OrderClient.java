package se.neckademin.customer.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import se.neckademin.customer.Model.Purchases;
import se.neckademin.customer.exception.UrlException;
import se.neckademin.customer.utility.Utility;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OrderClient {

    //    @Value("${PURCHASE_HOST}")
    private final static String host = "http://order-service:8081/";
    private final static String baseUrl = "purchases/purchase/";

    private Utility utility = new Utility();

    public List<Purchases> getAllPurchase(Long customerID) throws UrlException {

        log.info("Sending request to Order service with, host + baseUrl ={}", host + baseUrl + customerID.toString());
        URI url = utility.getUrl(host, baseUrl, customerID.toString());
        RestTemplate restTemplate = new RestTemplate();
        Purchases[] purchases = restTemplate.getForObject(url, Purchases[].class);

        if (purchases != null) {
            return Arrays.stream(purchases).collect(Collectors.toList());
        }
        return null;
    }

    public void addOrder(Purchases purchases) throws UrlException {
        log.info("Sending request to Order service with, host + baseUrl ={}", host + baseUrl + "add");
        URI addUri = utility.getUrl(host, baseUrl, "add");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(addUri, purchases, Purchases.class);
    }


}
