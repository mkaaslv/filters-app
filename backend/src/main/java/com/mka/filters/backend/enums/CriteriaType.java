package com.mka.filters.backend.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mka.filters.backend.enums.interfaces.IOperator;

import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum CriteriaType{
    NUMBER(1,"Amount", "number", NumberOperator.values()),
    TEXT(2, "Title", "text", TextOperator.values()),
    DATE(3, "Date", "date", DateOperator.values());

    private final int id;
    private final String label;
    private final String fieldType;
    private final IOperator[] operators;

    CriteriaType(int id, String label, String fieldType, IOperator[] operators) {
        this.id = id;
        this.label = label;
        this.fieldType = fieldType;
        this.operators = operators;
    }

    public static CriteriaType getById(int id) {
        for (CriteriaType type : CriteriaType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("No CriteriaType found with id: " + id);
    }
}
