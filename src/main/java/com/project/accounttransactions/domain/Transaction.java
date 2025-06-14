package com.project.accounttransactions.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@ToString
@Slf4j
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

    public Transaction(@NonNull Account account, @NonNull OperationType operationType, @NonNull BigDecimal amount) {
        this.account = account;
        this.operationType = operationType;
        BigDecimal absAmount = amount.abs();
        switch (this.operationType.getRegistrationType()) {
            case POSITIVE -> this.amount = absAmount;
            case NEGATIVE -> this.amount = absAmount.negate();
            default -> throw new IllegalArgumentException("Invalid registration type for operation: " + operationType);
        }
    }
}
