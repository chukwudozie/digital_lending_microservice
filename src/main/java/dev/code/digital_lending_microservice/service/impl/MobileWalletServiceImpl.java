package dev.code.digital_lending_microservice.service.impl;

import java.util.Optional;

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
                                                       final LoanProduct loanType) {
        final MobileWallet wallet = getWalletByAccountNumber(accountNumber);
        final double previousAmount = wallet.getWalletBalance();

        if (amount == 5000) {
            log.warn("User attempted to pay in %s".formatted(amount));
            throw new LoanException("please you not required to pay in %s".formatted(amount));
        }
        if (amount < 0 && previousAmount < (-amount - 500)) {
            log.warn("Money in wallet is too low for transaction ");
            return new MobileWalletStatusDTO(wallet, "FAILURE");
        }
        wallet.setWalletBalance(previousAmount + amount);

        final double interest = loanType.getInterest();
        final double loanRePayment = interest * amount / (1 - Math.pow((1 + interest), loanType.getTenure()));

        wallet.setPendingLoan(loanRePayment);
        return new MobileWalletStatusDTO(mobileWalletRepository.save(wallet), "SUCCESS");
    }

    @Override
    public MobileWallet getWallet(final String accountNumber) {
        return getWalletByAccountNumber(accountNumber);
    }

    private MobileWallet getWalletByAccountNumber(final String accountNumber) {
        log.info("looking for wallet with account number %s".formatted(accountNumber));
        Optional<MobileWallet> optWallet = mobileWalletRepository.findMobileWalletByCustomerPhoneNumber(accountNumber);
        return optWallet.orElseThrow(() -> new MobileWalletNotFoundException("wallet with account number %s not found".formatted(accountNumber)));
    }
}
