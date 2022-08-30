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


        final Customer customer = customerService.getCustomerByPhoneNumber(accountNumber);

        MobileWallet wallet = walletService.getWallet(accountNumber);

        if (Objects.equals(loanType, INVALID)) {
            String description = "LoanProduct selected is invalid :face_vomiting:";
            Transaction failedTransaction = createFailedTransaction(customer, amount, loanType, description);
            return transactionService.saveTransaction(failedTransaction);
        }


        double loanMaximumQualification = wallet.getLoanMaximumQualification();

        if (loanMaximumQualification < amount && wallet.getPendingLoan() != 0.0) {
            String description = "Not qualified for loan :face_with_head_bandage:";
            Transaction failedTransaction = createFailedTransaction(customer, amount, loanType, description);
            return transactionService.saveTransaction(failedTransaction);
        }


        final MobileWalletStatusDTO mobileWalletStatusDTO = walletService.addLoanAmountToWallet(accountNumber, amount, loanType
                , loanMaximumQualification);

        Transaction transaction =
                Transaction.builder()
                        .loanType(loanType.getType())
                        .amount(amount)
                        .status(mobileWalletStatusDTO.transactionStatus())
                        .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                        .customer(customer)
                        .description("transactions successful :tada:")
                        .build();
        return transactionService.saveTransaction(transaction);
    }

    private Transaction createFailedTransaction(final Customer customer, final double amount, LoanProduct loanType, String description) {
        return Transaction.builder()
                .amount(amount)
                .status("FAILED")
                .customer(customer)
                .loanType(loanType.getType())
                .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                .description(description)
                .build();

    }
}
