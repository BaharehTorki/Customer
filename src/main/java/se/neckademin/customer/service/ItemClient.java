package se.neckademin.customer.service;

import org.springframework.beans.factory.annotation.Value;
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

public class ItemClient {
    @Value("${ITEM_HOST}")
    private  String host;
    @Value("${ITEM_BASE_URL}")
    private  String baseUrl;
    private Utility utility = new Utility();

    public List<Item> getAllItems() throws UrlException {
        URI url = utility.getUrl(host, baseUrl, "all");
        RestTemplate restTemplate = new RestTemplate();
        Item[] objects = restTemplate.getForObject(url, Item[].class);
        return Arrays.stream(objects).collect(Collectors.toList());
    }

    public Item getItemById(Long id) throws UrlException {
        URI url = utility.getUrl(host, baseUrl, id.toString());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Item.class);
    }
}
