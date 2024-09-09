package com.desafioitau.api.transferencia.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BusinessNotFoundExceptionTest {

    @Test
    void testBusinessNotFoundExceptionWithMessage() {
        String errorMessage = "Resource not found";
        BusinessNotFoundException businessNotFoundException = new BusinessNotFoundException(errorMessage);
        assertEquals(errorMessage, businessNotFoundException.getMessage());
    }
}