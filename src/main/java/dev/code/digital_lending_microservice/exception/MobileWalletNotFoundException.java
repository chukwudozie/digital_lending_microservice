package dev.code.digital_lending_microservice.exception;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 30/08/2022
 */

public class MobileWalletNotFoundException extends RuntimeException {
    public MobileWalletNotFoundException(final String message) {
        super(message);
    }
}
