package dev.code.digital_lending_microservice.controller;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.payload.request.CustomerDto;
import dev.code.digital_lending_microservice.payload.response.CreateCustomerResponse;
import dev.code.digital_lending_microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CustomerDto customerDto){
        CreateCustomerResponse response = customerService.getCreateCustomerResponse(customerDto);
        return ResponseEntity.ok(response);
    }


}
