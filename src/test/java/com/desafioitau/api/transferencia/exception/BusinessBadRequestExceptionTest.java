package com.desafioitau.api.transferencia.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BusinessBadRequestExceptionTest {

    @Test
    void testBusinessBadRequestExceptionWithMessage() {
        String errorMessage = "This is a bad request";

        BusinessBadRequestException businessBadRequestException = new BusinessBadRequestException(errorMessage);
        assertEquals(errorMessage, businessBadRequestException.getMessage());
    }
}