package dev.code.digital_lending_microservice.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateCustomerResponse {
    private String status;
    private String fullName;
    private String accountNumber; //phoneNumber
    private LocalDateTime transactDate;
//    private double balance;

}
