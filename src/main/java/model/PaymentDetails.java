package model;


import java.util.Objects;
import java.util.Optional;


/**
 * Holder for customer's payment data. This class is immutable. Use {@link Builder} to create.
 *
 */
public final class PaymentDetails {
    /**
     * Builder for {@link PaymentDetails}.
     */
    public static class Builder {
        private String bankAccount;
        private String bankCode;
        private String bankIBAN;
        private String bankName;
        private String holderOfAccount;
        private Integer validityCheckResult;
        private String validityCheckMessage;

        public Builder bankAccount(String bBankAccount) {
            this.bankAccount = bBankAccount;
            return this;
        }

        public Builder bankCode(String bBankCode) {
            this.bankCode = bBankCode;
            return this;
        }

        public Builder bankIBAN(String bBankIBAN) {
            this.bankIBAN = bBankIBAN;
            return this;
        }

        public Builder bankName(String bBankName) {
            this.bankName = bBankName;
            return this;
        }

        public Builder holderOfAccount(String bHolderOfAccount) {
            this.holderOfAccount = bHolderOfAccount;
            return this;
        }

        public Builder validityCheckResult(Integer checkResult) {
            this.validityCheckResult = checkResult;
            return this;
        }

        public Builder validityCheckMessage(String message) {
            this.validityCheckMessage = message;
            return this;
        }

        public PaymentDetails build() {
            return new PaymentDetails(this);
        }

    }

    private final String bankAccount;
    private final String bankCode;
    private final String bankIBAN;
    private final String bankName;
    private final String holderOfAccount;
    private final Integer validityCheckResult;
    private final String validityCheckMessage;

    private PaymentDetails(Builder builder) {
        this.bankAccount = builder.bankAccount;
        this.bankCode = builder.bankCode;
        this.bankIBAN = builder.bankIBAN;
        this.bankName = builder.bankName;
        this.holderOfAccount = builder.holderOfAccount;
        this.validityCheckResult = builder.validityCheckResult;
        this.validityCheckMessage = builder.validityCheckMessage;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBankIBAN() {
        return bankIBAN;
    }

    public String getBankName() {
        return bankName;
    }

    public String getHolderOfAccount() {
        return holderOfAccount;
    }

    public Integer getValidityCheckResult() {
        return validityCheckResult;
    }

    public String getValidityCheckMessage() {
        return validityCheckMessage;
    }

    public boolean isValid() {
        if (validityCheckResult == null) {
            // Wenn das validityCheckResult nicht gesetzt wurden, wird zumindest überprüft, ob die wichtigen Felder
            // gesetzt sind

            return Optional.ofNullable(bankIBAN).orElse("").isEmpty() || Optional.ofNullable(bankAccount).orElse("").isEmpty()
                    && Optional.ofNullable(bankCode).orElse("").isEmpty();
        }
        return Integer.valueOf(0).equals(validityCheckResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccount, bankCode, bankIBAN, bankName, holderOfAccount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PaymentDetails other = (PaymentDetails) obj;
        return Objects.equals(bankAccount, other.bankAccount) //
            && Objects.equals(bankCode, other.bankCode) //
            && Objects.equals(bankIBAN, other.bankIBAN) //
            && Objects.equals(bankName, other.bankName) //
            && Objects.equals(holderOfAccount, other.holderOfAccount);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PaymentDetails [bankAccount=");
        builder.append(bankAccount);
        builder.append(", bankCode=");
        builder.append(bankCode);
        builder.append(", bankIBAN=");
        builder.append(bankIBAN);
        builder.append(", bankName=");
        builder.append(bankName);
        builder.append(", holderOfAccount=");
        builder.append(holderOfAccount);
        builder.append("]");
        return builder.toString();
    }
}
