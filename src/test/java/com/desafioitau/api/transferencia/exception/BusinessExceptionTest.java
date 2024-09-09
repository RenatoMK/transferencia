package com.desafioitau.api.transferencia.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BusinessExceptionTest {

    @Test
    void testBusinessExceptionWithMessage() {
        String errorMessage = "This is a business exception";
        BusinessException businessException = new BusinessException(errorMessage);
        assertEquals(errorMessage, businessException.getMessage());
    }
}