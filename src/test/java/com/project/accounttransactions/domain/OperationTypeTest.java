package com.project.accounttransactions.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTypeTest {

    @Test
    void testAllOperationTypesAreFoundById() {
        for (OperationType type : OperationType.values()) {
            assertEquals(type, OperationType.fromId(type.getId()));
        }
    }

    @Test
    void testInvalidOperationType() {
        assertThrows(IllegalArgumentException.class, () -> OperationType.fromId(999));
    }
}