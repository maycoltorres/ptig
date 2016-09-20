package com.gamasoft.hps.sab.webservices.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gamasoft.hps.sab.exception.ServiceException;

@ControllerAdvice
public class RestErrorHandler extends  ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)  
    @ResponseStatus(value=HttpStatus.CONFLICT)  
    @ResponseBody  
    public String handleServiceException(HttpServletRequest req, ServiceException ex) {  
        return ex.getMessage();  
    }
}