package com.project.accounttransactions.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionCreateRequestVO {

    @NotNull
    @JsonProperty(value = "account_id", required = true)
    private Long accountId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @JsonProperty(value = "operation_type_id", required = true)
    private Integer operationTypeId;
}
