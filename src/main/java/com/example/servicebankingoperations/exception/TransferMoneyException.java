package com.example.servicebankingoperations.exception;

import java.math.BigDecimal;

public class TransferMoneyException extends RuntimeException {
    public TransferMoneyException(BigDecimal transferSum) {
        super(String.format("This operation cannot be performed because the specified amount is greater than the sum: {} available on the account", transferSum));
    }
}
