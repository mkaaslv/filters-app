package com.mka.filters.backend.enums;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mka.filters.backend.enums.interfaces.IOperator;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DateOperator implements IOperator {
    BEFORE("BEFORE", "Before") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return compareDates((Date) operand1, (Date) operand2) < 0;
        }
    },
    AFTER("AFTER", "After") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return compareDates((Date) operand1, (Date) operand2) > 0;
        }
    },
    ON_DATE("ON_DATE", "On") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return compareDates((Date) operand1, (Date) operand2) == 0;
        }
    };

    private final String operator;
    private final String label;

    DateOperator(String operator, String label) {
        this.operator = operator;
        this.label = label;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public String getLabel() {
        return label;
    }

    // Helper method to compare dates
    int compareDates(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

}
