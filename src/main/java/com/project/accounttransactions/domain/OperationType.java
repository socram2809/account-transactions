package com.project.accounttransactions.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationType {
    NORMAL_PURCHASE(1, "Normal Purchase"),
    PURCHASE_WITH_INSTALLMENTS(2, "Purchase with installments"),
    WITHDRAWAL(3, "Withdrawal"),
    CREDIT_VOUCHER(4, "Credit Voucher");

    private final Integer id;
    private final String description;

    public static OperationType fromId(Integer id) {
        for (OperationType type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}
