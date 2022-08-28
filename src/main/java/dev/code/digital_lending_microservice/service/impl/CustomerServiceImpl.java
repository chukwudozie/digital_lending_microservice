package dev.code.digital_lending_microservice.service.impl;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.exception.LoanException;
import dev.code.digital_lending_microservice.payload.request.CustomerDto;
import dev.code.digital_lending_microservice.repository.CustomerRepository;
import dev.code.digital_lending_microservice.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(CustomerDto customer) {
        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())){
            throw new LoanException("Phone Number already exist");
        }

        if (customer.getPhoneNumber().length() != 11){
            throw new LoanException("Phone Number must be 11 digits");
        }

        if(customer.getFirstName() == null || customer.getLastName() == null
                || customer.getFirstName().isEmpty() || customer.getLastName().isEmpty()){
            throw new LoanException("Provide valid First name and last name");
        }
        Customer newCustomer = new Customer();
        getCustomerFromDto(newCustomer, customer);
       return customerRepository.save(newCustomer);

    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new LoanException("Invalid Id"+id));
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new LoanException("Invalid phone Number"));
    }

    @Override
    public Customer updateCustomer(CustomerDto customerDto, Long id) {
        Customer savedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new LoanException("Invalid Id "+id));
       getCustomerFromDto(savedCustomer, customerDto);
        return customerRepository.save(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    private void getCustomerFromDto(Customer customer, CustomerDto customerDto){
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

    }
}
