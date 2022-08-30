package dev.code.digital_lending_microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.code.digital_lending_microservice.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllTransactionsByCustomerPhoneNumber(String accountNumber);
}
