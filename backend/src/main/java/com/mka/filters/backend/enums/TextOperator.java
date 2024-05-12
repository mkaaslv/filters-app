package com.mka.filters.backend.enums;

import com.mka.filters.backend.enums.interfaces.IOperator;

public enum TextOperator implements IOperator {
    CONTAINS("contains", "Contains") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().contains(operand2.toString());
        }
    },
    NOT_CONTAINS("notContains", "Does not contain") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return !operand1.toString().contains(operand2.toString());
        }
    },
    STARTS_WITH("startsWith", "Starts with") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().startsWith(operand2.toString());
        }
    },
    ENDS_WITH("endsWith", "Ends with") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().endsWith(operand2.toString());
        }
    },
    MATCHES("matches", "Matches") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().matches(operand2.toString());
        }
    };

    private final String operator;
    private final String descriptor;

    TextOperator(String operator, String descriptor) {
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
