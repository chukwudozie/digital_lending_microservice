package dev.code.digital_lending_microservice.repository;

import dev.code.digital_lending_microservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    boolean existsByPhoneNumber(String phoneNumber);
}
