package com.project.accounttransactions.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCreateRequestVO {

    @NotBlank
    @JsonProperty(value = "document_number", required = true)
    private String documentNumber;
}
