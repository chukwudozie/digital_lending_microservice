package dev.code.digital_lending_microservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.code.digital_lending_microservice.domain.MobileWallet;

public interface MobileWalletRepository extends JpaRepository<MobileWallet, Long> {

    Optional<MobileWallet> findMobileWalletByCustomerPhoneNumber(String phoneNumber);
}
