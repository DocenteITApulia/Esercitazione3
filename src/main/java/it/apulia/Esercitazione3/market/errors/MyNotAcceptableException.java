package it.apulia.Esercitazione3.market.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class MyNotAcceptableException extends RuntimeException{
    public MyNotAcceptableException() {
        super();
    }
    public MyNotAcceptableException(String message, Throwable cause) {
        super(message, cause);
    }
    public MyNotAcceptableException(String message) {
        super(message);
    }
    public MyNotAcceptableException(Throwable cause) {
        super(cause);
    }
}
