package com.project.accounttransactions.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountCreateRequestVO {

    @NotBlank
    @JsonProperty(value = "document_number", required = true)
    private String documentNumber;

    @NotNull
    @JsonProperty(value = "available_credit_limit", required = true)
    private BigDecimal availableCreditLimit;
}
