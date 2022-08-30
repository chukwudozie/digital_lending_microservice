package dev.code.digital_lending_microservice.service;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.payload.request.CustomerDto;
import dev.code.digital_lending_microservice.payload.response.CreateCustomerResponse;

public interface CustomerService {

    Customer saveCustomer(CustomerDto customer);

    Customer getCustomerById(Long id);

    Customer getCustomerByPhoneNumber(String phoneNumber);

    Customer updateCustomer(CustomerDto customerDto, Long id);

    void deleteCustomer(Long id);

    CreateCustomerResponse getCreateCustomerResponse(CustomerDto customerDto);
}
