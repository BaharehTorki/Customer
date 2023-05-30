package se.neckademin.customer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import se.neckademin.customer.Model.Purchases;
import se.neckademin.customer.exception.UrlException;
import se.neckademin.customer.utility.Utility;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderClient {

    @Value("${PURCHASE_HOST}")
    private String host;
    @Value("${PURCHASE_BASE_URL}")
    private String baseUrl;

    private Utility utility = new Utility();

    public List<Purchases> getAllPurchase(Long customerID) throws UrlException {

        URI url = utility.getUrl(host, baseUrl, customerID.toString());
        RestTemplate restTemplate = new RestTemplate();
        Purchases[] purchases = restTemplate.getForObject(url, Purchases[].class);

        if (purchases != null) {
            return Arrays.stream(purchases).collect(Collectors.toList());
        }
        return null;
    }

    public void addOrder(Purchases purchases) throws UrlException {
        URI addUri = utility.getUrl(host, baseUrl, "add");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(addUri, purchases, Purchases.class);
    }


}
