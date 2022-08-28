package dev.code.digital_lending_microservice.service.impl;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.exception.LoanException;
import dev.code.digital_lending_microservice.payload.request.CreateCustomerDto;
import dev.code.digital_lending_microservice.repository.CustomerRepository;
import dev.code.digital_lending_microservice.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(CreateCustomerDto customer) {
        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())){
            throw new LoanException("Phone Number already exist");
        }
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setPhoneNumber(customer.getPhoneNumber());
       return customerRepository.save(newCustomer);

    }

    @Override
    public Customer getCustomerById(Long id) {
        return null;
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Customer updateCustomer(Long id) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
