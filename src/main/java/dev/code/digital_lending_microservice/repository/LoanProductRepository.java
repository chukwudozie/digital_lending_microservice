package dev.code.digital_lending_microservice.repository;

import dev.code.digital_lending_microservice.domain.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {
}
