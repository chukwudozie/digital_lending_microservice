package dev.code.digital_lending_microservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.code.digital_lending_microservice.domain.Transaction;
import dev.code.digital_lending_microservice.payload.response.TransactionResponse;
import dev.code.digital_lending_microservice.repository.TransactionRepository;
import dev.code.digital_lending_microservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 28/08/2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionResponse saveTransaction(final Transaction transactionRequest) {
        log.info("saving transactions....");
        Transaction transaction = transactionRepository.save(transactionRequest);
        return getTransactionResponse(transaction);
    }

    private TransactionResponse getTransactionResponse(final Transaction transaction) {
        return
                TransactionResponse.builder()
                        .transactDate(transaction.getCreatedAt())
                        .status(transaction.getStatus())
                        .amount(transaction.getAmount())
                        .loanType(transaction.getLoanType())
                        .accountNumber(transaction.getCustomer().getPhoneNumber())
                        .description(transaction.getDescription())
                        .build();
    }



    @Override
    public List<Transaction> getAllTransactionsByPhoneNumber(final String phoneNumber) {
        log.info("finding all transactions of %s".formatted(phoneNumber));
        return transactionRepository.findAllTransactionsByCustomerPhoneNumber(phoneNumber);
    }
}
