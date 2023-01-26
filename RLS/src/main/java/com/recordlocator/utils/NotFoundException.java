package com.recordlocator.utils;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("The locator id does not exist in our records");
    }
}
