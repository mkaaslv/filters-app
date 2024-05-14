package com.mka.filters.backend.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mka.filters.backend.enums.interfaces.IOperator;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NumberOperator implements IOperator {
    MORE_THAN("MORE_THAN", "More than") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 > (Double) operand2;
        }
    },
    LESS_THAN("LESS_THAN", "Less than") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 < (Double) operand2;
        }
    },
    EQUALS("EQUALS", "Equals") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return operand1.equals(operand2);
        }
    },
    GREATER_THAN_OR_EQUAL_TO("GREATER_THAN_OR_EQUAL_TO", "Greater than or equal to") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 >= (Double) operand2;
        }
    },
    LESS_THAN_OR_EQUAL_TO("LESS_THAN_OR_EQUAL_TO", "Less than or equal to") {
        @Override
        public boolean apply(Object operand1, Object operand2) {
            return (Double) operand1 <= (Double) operand2;
        }
    };

    private final String operator;
    private final String label;

    NumberOperator(String operator, String label) {
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
}
