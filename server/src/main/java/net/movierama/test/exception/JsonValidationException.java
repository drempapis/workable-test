package net.movierama.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Json Not Valid")
public class JsonValidationException extends RuntimeException {

    public JsonValidationException(final String message, final Exception e) {
        super(message, e);
    }
}
