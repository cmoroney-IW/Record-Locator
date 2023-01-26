package com.medtech.utils;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("That patient id does not exist in our records");
    }
}
