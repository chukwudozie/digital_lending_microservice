package dev.code.digital_lending_microservice.payload.request;

import lombok.Data;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 29/08/2022
 */

@Data
public class LoanRequest {
    private String accountNumber;
    private Double amount;
    private String type;
}
