package com.ceiia.challenge.exceptions;

public class CityAlreadyRegisteredException extends Exception {
    public CityAlreadyRegisteredException(String errorMessage) {
        super(errorMessage);
    }
}
