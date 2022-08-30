package dev.code.digital_lending_microservice.exception;

public class WalletExistsException extends RuntimeException {
    public WalletExistsException(String message) {
        super(message);
    }
}
