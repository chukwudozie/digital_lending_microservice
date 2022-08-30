package dev.code.digital_lending_microservice.service;

import java.util.List;

import dev.code.digital_lending_microservice.domain.Transaction;
import dev.code.digital_lending_microservice.payload.response.TransactionResponse;

/**
 * Created by @author Ifeanyichukwu Otiwa
 *28/08/2022
 */

public interface TransactionService {
    TransactionResponse saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactionsByPhoneNumber(String phoneNumber);
}
