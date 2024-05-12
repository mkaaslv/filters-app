package com.mka.filters.backend.enums;

import com.mka.filters.backend.enums.interfaces.IOperator;

public enum NumberOperator implements IOperator {
    MORE_THAN(">", "More than") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 > (Double) operand2;
        }
    },
    LESS_THAN("<", "Less than") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 < (Double) operand2;
        }
    },
    EQUALS("==", "Equals") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.equals(operand2);
        }
    },
    GREATER_THAN_OR_EQUAL_TO(">=", "Greater than or equal to") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 >= (Double) operand2;
        }
    },
    LESS_THAN_OR_EQUAL_TO("<=", "Less than or equal to") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 <= (Double) operand2;
        }
    };

    private final String operator;
    private final String descriptor;

    NumberOperator(String operator, String descriptor) {
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
}

