package dev.code.digital_lending_microservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class LendExceptionHandler {
    @ExceptionHandler(LoanException.class)
    public ResponseEntity<Object> registrationRequestHandler(LoanException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String errorType = e.getClass().getSimpleName().toUpperCase();
        ExceptionPayload payLoad = new ExceptionPayload(e.getMessage(),e, errorType, badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(payLoad,badRequest);

    }

    @ExceptionHandler(MobileWalletNotFoundException.class)
    public ResponseEntity<Object> walletNotFoundRequestHandler(MobileWalletNotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        String errorType = e.getClass().getSimpleName().toLowerCase();
        ExceptionPayload payLoad = new ExceptionPayload(e.getMessage(),e, errorType, notFound,
                ZonedDateTime.now(ZoneId.of("UTC")));
        return new ResponseEntity<>(payLoad,notFound);
    }

    @ExceptionHandler(WalletExistsException.class)
    public ResponseEntity<Object> walletAlreadyExistsHandler(WalletExistsException e){
        HttpStatus notFound = HttpStatus.NOT_ACCEPTABLE;
        String errorType = e.getClass().getSimpleName().toLowerCase();
        ExceptionPayload payLoad = new ExceptionPayload(e.getMessage(),e, errorType, notFound,
                ZonedDateTime.now(ZoneId.of("UTC")));
        return new ResponseEntity<>(payLoad,notFound);
    }
}
