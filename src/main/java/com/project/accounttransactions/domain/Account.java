package com.project.accounttransactions.domain;

import com.project.accounttransactions.exception.AvaliableCreditLimitCannotBeNegativeException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank
    private String documentNumber;

    @NonNull
    @NotNull
    @Positive
    private BigDecimal availableCreditLimit;

    public void updateAvailableCreditLimit(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        this.availableCreditLimit = this.availableCreditLimit.add(amount);
        if(this.availableCreditLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new AvaliableCreditLimitCannotBeNegativeException("Available credit limit cannot be negative");
        }
    }
}
