package com.w2m.superheroes.domain.exception;

public class SuperheroAlreadyExistsException extends RuntimeException {

    public SuperheroAlreadyExistsException(String message) {
        super(message);
    }
}
