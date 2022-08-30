package dev.code.digital_lending_microservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoanProductUnitTest {

    private LoanProduct loanProductA;
    private LoanProduct loanProductB;


    @BeforeEach
    void setUp() {
        loanProductA = LoanProduct.PRODUCT_A;
        loanProductB = LoanProduct.PRODUCT_B;
    }

    @Test
    void testLoanProductA_shouldReturnType() {
        assertThat(loanProductA.getType()).isEqualTo("PRODUCT A");
    }

    @Test
    void testLoanProductB_shouldReturnType() {
        assertThat(loanProductB.getType()).isEqualTo("PRODUCT B");
    }

    @Test
    void testLoanProductAMaxAllowance_shouldReturn10000() {
        assertThat(loanProductA.getMaxAllowance()).isEqualTo(10000);
    }

    @Test
    void testLoanProductBMaxAllowance_shouldReturn25000() {
        assertThat(loanProductB.getMaxAllowance()).isEqualTo(25000);
    }


    @Test
    void testLoanProductAInterest_shouldReturn10() {
        assertThat(loanProductA.getInterest()).isEqualTo(10.0);
    }


    @Test
    void testLoanProductBInterest_shouldReturn12_5() {
        assertThat(loanProductB.getInterest()).isEqualTo(12.5);
    }


    @Test
    void testLoanProductATenure_shouldReturn15() {
        assertThat(loanProductA.getTenure()).isEqualTo(15);
    }

    @Test
    void testLoanProductBTenure_shouldReturn30() {
        assertThat(loanProductB.getTenure()).isEqualTo(30);
    }

    @Test
    void testLoanProductAConsistency() {
        final LoanProduct secondLoanProductA = LoanProduct.PRODUCT_A;
        assertThat(secondLoanProductA).isEqualTo(loanProductA);
    }

    @Test
    void testLoanProductBConsistency() {
        final LoanProduct secondLoanProductB = LoanProduct.PRODUCT_B;
        assertThat(secondLoanProductB).isEqualTo(loanProductB);
    }

    @Test
    void testVerifyLoanTypeWhenGivenAStringValue_shouldNotBeNull() {
        LoanProduct typeA = LoanProduct.getEnum("A");
        LoanProduct typeB = LoanProduct.getEnum("rb");
        LoanProduct typeC = LoanProduct.getEnum("rrrr");
        assertThat(typeA).isNotNull();
        assertThat(typeB).isNotNull();
        assertThat(typeC).isEqualTo(LoanProduct.INVALID);
    }
}