package com.mka.filters.backend.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mka.filters.backend.enums.interfaces.IOperator;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TextOperator implements IOperator {
    CONTAINS("CONTAINS", "Contains") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().contains(operand2.toString());
        }
    },
    NOT_CONTAINS("NOT_CONTAINS", "Does not contain") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return !operand1.toString().contains(operand2.toString());
        }
    },
    STARTS_WITH("STARTS_WITH", "Starts with") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().startsWith(operand2.toString());
        }
    },
    ENDS_WITH("ENDS_WITH", "Ends with") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().endsWith(operand2.toString());
        }
    },
    MATCHES("MATCHES", "Matches") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.toString().matches(operand2.toString());
        }
    };

    private final String operator;
    private final String label;

    TextOperator(String operator, String label) {
        this.operator = operator;
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getOperator() {
        return operator;
    }
}
