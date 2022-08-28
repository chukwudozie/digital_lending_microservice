package dev.code.digital_lending_microservice.payload.request;

import lombok.Data;

@Data
public class CreateCustomerDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;
}
