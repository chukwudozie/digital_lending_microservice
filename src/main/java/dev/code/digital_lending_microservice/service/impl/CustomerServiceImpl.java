package dev.code.digital_lending_microservice.service.impl;

import dev.code.digital_lending_microservice.domain.MobileWallet;
import dev.code.digital_lending_microservice.payload.response.CreateCustomerResponse;
import dev.code.digital_lending_microservice.service.MobileWalletService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.code.digital_lending_microservice.domain.Customer;
import dev.code.digital_lending_microservice.exception.LoanException;
import dev.code.digital_lending_microservice.payload.request.CustomerDto;
import dev.code.digital_lending_microservice.repository.CustomerRepository;
import dev.code.digital_lending_microservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MobileWalletService walletService;

    public CustomerServiceImpl(CustomerRepository customerRepository, MobileWalletService walletService) {
        this.customerRepository = customerRepository;
        this.walletService = walletService;
    }

    @Override
    public Customer saveCustomer(CustomerDto customer) {
        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
            throw new LoanException("Phone Number already exist");
        }

        if (customer.getPhoneNumber().length() != 11) {
            throw new LoanException("Phone Number must be 11 digits");
        }

        if (customer.getFirstName() == null || customer.getLastName() == null
                || customer.getFirstName().isEmpty() || customer.getLastName().isEmpty()) {
            throw new LoanException("Provide valid First name and last name");
        }
        Customer newCustomer = new Customer();
        getCustomerFromDto(newCustomer, customer);
        Customer savedCustomer = customerRepository.save(newCustomer);
        final MobileWallet createdWallet = walletService.createWallet(savedCustomer);
        if (createdWallet == null) {
            log.info("Wallet was not created");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create wallet");
        }
        log.info("Wallet created with account number: %s".formatted(createdWallet.getCustomer().getPhoneNumber()));
        return savedCustomer;

    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new LoanException("Invalid Id" + id));
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new LoanException("Invalid phone Number"));
    }

    @Override
    public Customer updateCustomer(CustomerDto customerDto, Long id) {
        Customer savedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new LoanException("Invalid Id " + id));
        getCustomerFromDto(savedCustomer, customerDto);
        return customerRepository.save(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new LoanException("Invalid id " + id));
        customerRepository.delete(customer);

    }

    @Override
    public CreateCustomerResponse getCreateCustomerResponse(CustomerDto customerDto) {
        Customer customer = saveCustomer(customerDto);
        return CreateCustomerResponse.builder()
                .accountNumber(customer.getPhoneNumber())
                .status("SUCCESS")
                .transactDate(LocalDateTime.now())
                .fullName("%s %s".formatted(customer.getFirstName(),customer.getLastName()))
                .build();
    }

    private void getCustomerFromDto(Customer customer, CustomerDto customerDto) {
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

    }
}
