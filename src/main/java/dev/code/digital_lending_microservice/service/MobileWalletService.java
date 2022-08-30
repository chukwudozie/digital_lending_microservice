package dev.code.digital_lending_microservice.service;

import dev.code.digital_lending_microservice.domain.MobileWallet;
import dev.code.digital_lending_microservice.payload.request.MobileWalletStatusDTO;

public interface MobileWalletService {

    MobileWalletStatusDTO addAmountToWallet(String accountNumber, Double amount);

}
