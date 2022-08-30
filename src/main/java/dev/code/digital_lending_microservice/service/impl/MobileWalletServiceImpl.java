package dev.code.digital_lending_microservice.service.impl;

import java.util.Optional;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.exception.WalletExistsException;
import dev.code.digital_lending_microservice.payload.request.CustomerDto;
import org.springframework.stereotype.Service;

import dev.code.digital_lending_microservice.domain.LoanProduct;
import dev.code.digital_lending_microservice.domain.MobileWallet;
import dev.code.digital_lending_microservice.exception.LoanException;
import dev.code.digital_lending_microservice.exception.MobileWalletNotFoundException;
import dev.code.digital_lending_microservice.payload.request.MobileWalletStatusDTO;
import dev.code.digital_lending_microservice.repository.MobileWalletRepository;
import dev.code.digital_lending_microservice.service.MobileWalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 29/08/2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class MobileWalletServiceImpl implements MobileWalletService {
    private final MobileWalletRepository mobileWalletRepository;

    @Override
    public MobileWalletStatusDTO addLoanAmountToWallet(final String accountNumber, final Double amount,
                                                       final LoanProduct loanType, double maxQualification) {
        final MobileWallet wallet = getWalletByAccountNumber(accountNumber);
        final double previousAmount = wallet.getWalletBalance();

        if (amount == 5000 || amount < 0) {
            log.warn("User attempted to pay in %s".formatted(amount));
            throw new LoanException("please you are not required to pay in %f".formatted(amount));
        }
        wallet.setWalletBalance(previousAmount + amount);

        if (maxQualification > 0) {
            wallet.setLoanMaximumQualification(Math.round(maxQualification - amount));
        } else {
            wallet.setLoanMaximumQualification(Math.round(loanType.getMaxAllowance() - amount));
        }

        final double interest = loanType.getInterest() / 100;
        final int numberOfYears = loanType.getTenure();

        final double loanRePayment = calculateLoanRePayment(amount, interest, numberOfYears);


        log.debug("loan repayment amount %s".formatted(loanRePayment));

        log.info("setting pending loan");

        final double previouslyAcquiredLoan = wallet.getPendingLoan();


        wallet.setPendingLoan(loanRePayment + previouslyAcquiredLoan);
        log.info("pending loan %s".formatted(wallet.toString()));

        log.info("saving wallet  " + wallet);
        return new MobileWalletStatusDTO(mobileWalletRepository.save(wallet), "SUCCESS");
    }

    private double calculateLoanRePayment(final Double amount, final double interest, final int numberOfYears) {
        return amount * Math.pow(1 + interest, numberOfYears);
    }

    @Override
    public MobileWallet getWallet(final String accountNumber) {
        return getWalletByAccountNumber(accountNumber);
    }

    @Override
    public MobileWallet createWallet(Customer customer) {
        String phoneNumber = customer.getPhoneNumber();
        Optional<MobileWallet> mobileWalletByCustomerPhoneNumber = mobileWalletRepository.findMobileWalletByCustomerPhoneNumber(phoneNumber);
        if (mobileWalletByCustomerPhoneNumber.isPresent()){
            throw new WalletExistsException("Wallet already exists");
        }
        MobileWallet wallet = MobileWallet.builder()
                .walletBalance(0.00)
                .pendingLoan(0.00)
                .customer(customer)
                .loanMaximumQualification(0)
                .build();
        return mobileWalletRepository.save(wallet);
    }

    private MobileWallet getWalletByAccountNumber(final String accountNumber) {
        log.info("looking for wallet with account number %s".formatted(accountNumber));
        Optional<MobileWallet> optWallet = mobileWalletRepository.findMobileWalletByCustomerPhoneNumber(accountNumber);
        return optWallet.orElseThrow(() -> new MobileWalletNotFoundException("wallet with account number %s not found".formatted(accountNumber)));
    }

    public MobileWalletStatusDTO withdrawFromWallet(String accountNumber, Double amount) {
        final MobileWallet wallet = getWalletByAccountNumber(accountNumber);
        final double previousAmount = wallet.getWalletBalance();
        if (previousAmount - amount - 500 < 0) {
            log.warn("Money in wallet is too low for transaction ");
            return new MobileWalletStatusDTO(wallet, "FAILURE");
        }
        return null; //TODO create logic for Withdrawal
    }
}
