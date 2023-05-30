package se.neckademin.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UrlException extends Throwable{

    public UrlException(String message){
        super(message);
    }



}