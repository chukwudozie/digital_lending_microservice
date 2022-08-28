package dev.code.digital_lending_microservice.service;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.payload.request.CreateCustomerDto;

public interface CustomerService {

    Customer saveCustomer(CreateCustomerDto customer);

    Customer getCustomerById(Long id);

    Customer getCustomerByPhoneNumber(String phoneNumber);

    Customer updateCustomer(Long id);

    void deleteCustomer(Long id);
}
