package com.mka.filters.backend.enums;

import com.mka.filters.backend.enums.interfaces.IOperator;

import lombok.Getter;

@Getter
public enum CriteriaType {
    NUMBER(1, "Amount", NumberOperator.values()),
    TEXT(2, "Title", TextOperator.values()),
    DATE(3, "Date", DateOperator.values());

    private final int id;
    private final String label;
    private final IOperator[] operators;

    CriteriaType(int id, String label, IOperator[] operators) {
        this.id = id;
        this.label = label;
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
