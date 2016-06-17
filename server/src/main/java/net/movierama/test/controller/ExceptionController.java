package net.movierama.test.controller;

import net.movierama.test.controller.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleAnyException(final HttpServletRequest request, final Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "";

        final ResponseStatus responseStatus = e.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            status = responseStatus.value();
            message = responseStatus.reason();
        }

        logger.error("Path:[{}], Method:[{}], Exception:[{}], Message:[{}]",
                request.getRequestURI(), request.getMethod(), e.getClass().getSimpleName(), message);
        return errorResponse(status, message);
    }

    private ResponseEntity<ExceptionResponse> errorResponse(final HttpStatus status, final String message) {
        return new ResponseEntity<>(new ExceptionResponse(status.toString(), message), new HttpHeaders(), status);
    }
}
