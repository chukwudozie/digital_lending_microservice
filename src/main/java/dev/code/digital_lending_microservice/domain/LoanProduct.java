package dev.code.digital_lending_microservice.domain;


/**
 * An {@link Enum} is used for LoanProduct types to ensure that only {@link LoanProduct.PRODUCT_A} and
 * {@link LoanProduct.PRODUCT_B} are created during runtime
 */
public enum LoanProduct {
    PRODUCT_A(10000, 10.0, 15) {
        @Override
        public String getType() {
            return "PRODUCT A";
        }
    },
    PRODUCT_B(25000, 12.5, 30) {
        @Override
        public String getType() {
            return "PRODUCT B";
        }
    };
    private final double maxAllowance;

    private final double interest;

    private final int tenure;

    LoanProduct(final Integer maxAllowance, final Double interest, final Integer tenure) {
        this.maxAllowance = maxAllowance;
        this.tenure = tenure;
        this.interest = interest;
    }

    public double getMaxAllowance() {
        return maxAllowance;
    }

    public double getInterest() {
        return interest;
    }

    public int getTenure() {
        return tenure;
    }

    public abstract String getType();
}
