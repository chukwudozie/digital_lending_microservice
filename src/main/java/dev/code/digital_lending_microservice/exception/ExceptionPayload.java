package dev.code.digital_lending_microservice.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ExceptionPayload {

    private final String errorType;
    private final String message                ;
    private final HttpStatus status;
    private final ZonedDateTime time;


    public ExceptionPayload(String errorType, Throwable t, String message, HttpStatus status, ZonedDateTime time) {
        this.errorType = errorType;
        this.message = message;
        this.status = status;
        this.time = time;
    }
}
