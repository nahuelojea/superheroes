package com.w2m.superheroes.domain.exception;

public class SuperheroNotFoundException extends RuntimeException {

    public SuperheroNotFoundException(String message) {
        super(message);
    }
}

