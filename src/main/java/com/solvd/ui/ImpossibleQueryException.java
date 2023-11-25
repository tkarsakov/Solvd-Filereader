package com.solvd.ui;

import java.util.InputMismatchException;

public class ImpossibleQueryException extends InputMismatchException {
    public ImpossibleQueryException(String message) {
        super(message);
    }
}
