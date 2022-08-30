package dev.code.digital_lending_microservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.code.digital_lending_microservice.logic.BusinessLogic;
import dev.code.digital_lending_microservice.payload.request.LoanRequest;
import dev.code.digital_lending_microservice.payload.response.TransactionResponse;
import dev.code.digital_lending_microservice.service.MobileWalletService;
import dev.code.digital_lending_microservice.service.TransactionService;
import lombok.RequiredArgsConstructor;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 29/08/2022
 */

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final BusinessLogic service;


    @PostMapping("/request")
    public ResponseEntity<TransactionResponse> requestLoan(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(service.giveLoanToCustomer(request));
    }
}
