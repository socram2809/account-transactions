package com.project.accounttransactions.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.accounttransactions.domain.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionResponseVO {

    @JsonProperty(value = "transaction_id")
    private Long id;

    @JsonProperty(value = "account_id")
    private Long accountId;

    @JsonProperty(value = "operation_type_id")
    private Integer operationTypeId;

    private BigDecimal amount;

    public static TransactionResponseVO from(Transaction transaction) {
        TransactionResponseVO response = new TransactionResponseVO();
        response.setId(transaction.getId());
        response.setAccountId(transaction.getAccount().getId());
        response.setOperationTypeId(transaction.getOperationType().getId());
        response.setAmount(transaction.getAmount());
        return response;
    }
}
