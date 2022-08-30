package dev.code.digital_lending_microservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "mobile_wallet")
public class MobileWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long loanMaximumQualification;

    @OneToOne
    @JoinColumn(
            name = "account_number",
            referencedColumnName = "phone_number"
    )
    private Customer customer;

    private double walletBalance;

    @Column(name = "pending_loan")
    private double pendingLoan;
}
