package com.desafioitau.api.transferencia.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class HttpErroExceptionTest {

    @InjectMocks
    private HttpErroException httpErroException;

    @Mock
    private BusinessBadRequestException businessBadRequestException;
    
    @Mock
    private BusinessNotFoundException businessNotFoundException;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBusinessBadRequest() throws Exception{
    	ResponseEntity<?> retorno = httpErroException.businessBadRequest(businessBadRequestException);
    	assertTrue(retorno.getStatusCode().toString().contains("400 BAD_REQUEST"));
    }
    
    
    @Test
    void testBusinessNotFound() throws Exception{
    	ResponseEntity<?> retorno = httpErroException.businessNotFound(businessNotFoundException);
    	assertTrue(retorno.getStatusCode().toString().contains("404 NOT_FOUND"));
    }
    
    
}