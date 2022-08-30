package dev.code.digital_lending_microservice.service;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.domain.LoanProduct;
import dev.code.digital_lending_microservice.domain.MobileWallet;
import dev.code.digital_lending_microservice.payload.request.MobileWalletStatusDTO;

public interface MobileWalletService {

    MobileWalletStatusDTO addLoanAmountToWallet(String accountNumber, Double amount, LoanProduct loanType,
                                                double loanMaximumQualification);

    MobileWallet getWallet(String accountNumber);

    MobileWallet createWallet(Customer customer);
}
