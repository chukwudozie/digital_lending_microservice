package dev.code.digital_lending_microservice.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class CustomerDto {


    @NotNull
    @NotBlank(message = "provide your firstname")
    private String firstName;

    @NotNull
    @NotBlank(message = "provide your lastname")
    private String lastName;

    @NotNull
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Phone Number should contain 11 digits")
    private String phoneNumber;

    @NotNull
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull
    @NotBlank(message = "provide your password")
    private String password;
}
