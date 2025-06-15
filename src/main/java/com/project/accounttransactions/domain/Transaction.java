package com.project.accounttransactions.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Convert(converter = OperationTypeConverter.class)
    private OperationType operationType;

    private BigDecimal amount;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime eventDate;

    public Transaction(Account account, OperationType operationType, BigDecimal amount) {
        if(account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if(operationType == null) {
            throw new IllegalArgumentException("OperationType cannot be null");
        }
        if(amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        this.account = account;
        this.operationType = operationType;
        BigDecimal absAmount = amount.abs();
        switch (this.operationType.getRegistrationType()) {
            case POSITIVE -> this.amount = absAmount;
            case NEGATIVE -> this.amount = absAmount.negate();
        }
    }
}
