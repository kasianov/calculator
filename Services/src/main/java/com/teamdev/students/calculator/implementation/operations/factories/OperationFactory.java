package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.intefaces.Operation;

public interface OperationFactory<Result,OperationError extends Exception> {
    Operation<Result,OperationError> createOperation();
}
