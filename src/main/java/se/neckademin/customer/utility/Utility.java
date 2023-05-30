package se.neckademin.customer.utility;

import lombok.extern.slf4j.Slf4j;
import se.neckademin.customer.exception.UrlException;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class Utility {

    public URI getUrl(String host, String baseUrl, String pathVariable) throws UrlException {
        try {
            return new URI(host + baseUrl + pathVariable);
        } catch (URISyntaxException e) {
            log.error("The url={} is not reachable", host + baseUrl + pathVariable);
            throw new UrlException(e.getMessage());
        }
    }


}
