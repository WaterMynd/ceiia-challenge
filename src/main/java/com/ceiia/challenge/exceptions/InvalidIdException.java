package com.ceiia.challenge.exceptions;

public class InvalidIdException extends Exception {
    public InvalidIdException(String errorMessage) {
        super(errorMessage);
    }
}