package dev.code.digital_lending_microservice.logic;

import static dev.code.digital_lending_microservice.domain.LoanProduct.INVALID;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import org.springframework.stereotype.Service;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.domain.LoanProduct;
import dev.code.digital_lending_microservice.domain.MobileWallet;
import dev.code.digital_lending_microservice.domain.Transaction;
import dev.code.digital_lending_microservice.payload.request.LoanRequest;
import dev.code.digital_lending_microservice.payload.request.MobileWalletStatusDTO;
import dev.code.digital_lending_microservice.payload.response.TransactionResponse;
import dev.code.digital_lending_microservice.service.CustomerService;
import dev.code.digital_lending_microservice.service.MobileWalletService;
import dev.code.digital_lending_microservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 30/08/2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessLogic {
    private final TransactionService transactionService;
    private final MobileWalletService walletService;
    private final CustomerService customerService;

    public TransactionResponse giveLoanToCustomer(LoanRequest request) {
        final String type = request.getType();
        final double amount = request.getAmount();
        final String accountNumber = request.getAccountNumber();

        final LoanProduct loanType = LoanProduct.getEnum(type);

        if (Objects.equals(loanType, INVALID)) {
            return getFailedTransactionResponse(amount, accountNumber);
        }


        final Customer customer = customerService.getCustomerByPhoneNumber(accountNumber);

        MobileWallet wallet = walletService.getWallet(accountNumber);

        final double customerPreviouslyAcquiredLoan = wallet.getPendingLoan();



        if (customerPreviouslyAcquiredLoan != 0.0) {
            return getFailedTransactionResponse(amount, accountNumber);
        }

        final MobileWalletStatusDTO mobileWalletStatusDTO = walletService.addLoanAmountToWallet(accountNumber, amount, loanType);

        Transaction transaction =
                Transaction.builder()
                        .loanType(loanType.getType())
                        .amount(amount)
                        .status(mobileWalletStatusDTO.transactionStatus())
                        .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                        .customer(customer)
                        .build();
        return transactionService.saveTransaction(transaction);
    }

    private static TransactionResponse getFailedTransactionResponse(final double amount, final String accountNumber) {
        log.warn("Loan Type requested Not valid");
        return TransactionResponse.builder()
                .amount(amount)
                .accountNumber(accountNumber)
                .status("FAILED")
                .transactDate(LocalDateTime.now(ZoneId.of("UTC")))
                .loanType("INVALID")
                .build();
    }
}
