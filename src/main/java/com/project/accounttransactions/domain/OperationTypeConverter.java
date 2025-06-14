package com.project.accounttransactions.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OperationTypeConverter implements AttributeConverter<OperationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OperationType attribute) {
        return attribute != null ? attribute.getId() : null;
    }

    @Override
    public OperationType convertToEntityAttribute(Integer dbData) {
        return dbData != null ? OperationType.fromId(dbData) : null;
    }
}
