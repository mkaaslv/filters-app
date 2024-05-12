package com.mka.filters.backend.enums.interfaces;

public interface IOperator {
    // Apply the operator on two operands and return the result
    boolean apply(Object operand1, Object operand2);

    // Get a human-readable representation of the operator
    String getDescriptor();

    // Get the operator
    String getOperator();
}
