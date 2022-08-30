package dev.code.digital_lending_microservice.payload.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 28/08/2022
 */

@Data
@Builder
public class TransactionResponse {
    private LocalDateTime transactDate;
    private String status;
    private double amount;
    private String loanType;
    private String accountNumber;
    private String description;
}
