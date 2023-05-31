package se.neckademin.customer.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.neckademin.customer.Model.Item;
import se.neckademin.customer.exception.UrlException;
import se.neckademin.customer.utility.Utility;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ItemClient {
    private final static String host ="item-service:8082/";
    private final static String baseUrl = "item/";
    private Utility utility = new Utility();

    public List<Item> getAllItems() throws UrlException {
        log.info("Sending request to Item service with, host + baseUrl ={}", host + baseUrl+"all");
        URI url = utility.getUrl(host, baseUrl, "all");
        RestTemplate restTemplate = new RestTemplate();
        Item[] objects = restTemplate.getForObject(url, Item[].class);
        return Arrays.stream(objects).collect(Collectors.toList());
    }

    public Item getItemById(Long id) throws UrlException {
        log.info("Sending request to Item service with, host + baseUrl ={}", host + baseUrl+ id.toString());
        URI url = utility.getUrl(host, baseUrl, id.toString());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Item.class);
    }
}
