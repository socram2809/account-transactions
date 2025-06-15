package com.project.accounttransactions.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest {

    @Test
    void shouldCreateTransaction_whenValidParametersWithNegativeAmount() {
        Account account = new Account("12345678900");
        Transaction transaction = new Transaction(account, OperationType.NORMAL_PURCHASE, BigDecimal.TEN);
        assertEquals(account, transaction.getAccount());
        assertEquals(OperationType.NORMAL_PURCHASE, transaction.getOperationType());
        assertEquals(BigDecimal.TEN.negate(), transaction.getAmount());
    }

    @Test
    void shouldCreateTransaction_whenValidParametersWithPositiveAmount() {
        Account account = new Account("12345678900");
        Transaction transaction = new Transaction(account, OperationType.CREDIT_VOUCHER, BigDecimal.TEN);
        assertEquals(account, transaction.getAccount());
        assertEquals(OperationType.CREDIT_VOUCHER, transaction.getOperationType());
        assertEquals(BigDecimal.TEN, transaction.getAmount());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenAccountIsNull() {
        assertEquals(
                "Account cannot be null",
                assertThrows(IllegalArgumentException.class, () -> new Transaction(null, OperationType.NORMAL_PURCHASE, BigDecimal.TEN)).getMessage()
        );
    }

    @Test
    void shouldThrowIllegalArgumentException_whenOperationTypeIsNull() {
        Account account = new Account("12345678900");
        assertEquals(
                "OperationType cannot be null",
                assertThrows(IllegalArgumentException.class, () -> new Transaction(account, null, BigDecimal.TEN)).getMessage()
        );
    }

    @Test
    void shouldThrowIllegalArgumentException_whenAmountIsNull() {
        Account account = new Account("12345678900");
        assertEquals(
                "Amount cannot be null",
                assertThrows(IllegalArgumentException.class, () -> new Transaction(account, OperationType.NORMAL_PURCHASE, null)).getMessage()
        );
    }
}
