package com.project.accounttransactions.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.accounttransactions.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountResponseVO {

    @JsonProperty(value = "account_id")
    private Long id;

    @JsonProperty(value = "document_number")
    private String documentNumber;

    @JsonProperty(value = "available_credit_limit")
    private BigDecimal availableCreditLimit;

    public static AccountResponseVO from(Account account) {
        AccountResponseVO response = new AccountResponseVO();
        response.setId(account.getId());
        response.setDocumentNumber(account.getDocumentNumber());
        response.setAvailableCreditLimit(account.getAvailableCreditLimit());
        return response;
    }
}
