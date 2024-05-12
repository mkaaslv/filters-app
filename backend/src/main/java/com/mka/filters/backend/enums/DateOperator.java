package com.mka.filters.backend.enums;

import java.sql.Date;

import com.mka.filters.backend.enums.interfaces.IOperator;

public enum DateOperator implements IOperator {
    FROM(">=", "From") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return compareDates((Date) operand1, (Date) operand2) >= 0;
        }
    },
    BEFORE("<", "Before") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return compareDates((Date) operand1, (Date) operand2) < 0;
        }
    },
    AFTER(">", "After") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return compareDates((Date) operand1, (Date) operand2) > 0;
        }
    },
    ON_DATE("==", "On") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return compareDates((Date) operand1, (Date) operand2) == 0;
        }
    };

    private final String operator;
    private final String descriptor;

    DateOperator(String operator, String descriptor) {
        this.operator = operator;
        this.descriptor = descriptor;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    // Helper method to compare dates
    int compareDates(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

}
