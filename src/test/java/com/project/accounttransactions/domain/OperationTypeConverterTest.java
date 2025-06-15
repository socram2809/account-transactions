package com.project.accounttransactions.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTypeConverterTest {

    private final OperationTypeConverter converter = new OperationTypeConverter();

    @Test
    void testConvertToDatabaseColumn() {
        for (OperationType type : OperationType.values()) {
            assertEquals(type.getId(), converter.convertToDatabaseColumn(type));
        }
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void testConvertToEntityAttribute() {
        for (OperationType type : OperationType.values()) {
            assertEquals(type, converter.convertToEntityAttribute(type.getId()));
        }
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void testConvertToEntityAttributeWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute(999));
    }
}