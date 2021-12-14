package com.bridgelabz.addressbook.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Purpose: To Handle Global Exception
 *
 * @author : Kunal Suryawanshi
 * @since : 13-12-2021
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Purpose To Override Method For Validation Status
     *
     * @param ex      reference used for exception
     * @param headers reference for http header
     * @param status  gives the http status
     * @param request reference to handle request
     * @return Error Message with Status
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * Purpose To Handle Entity Not Found Exception
     *
     * @param ex To Specify Reference
     * @return Error Message With Status
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>("Given Id is Not Found", HttpStatus.BAD_REQUEST);
    }

    /**
     * Purpose To Handle Constraint Violation Exception
     *
     * @param request a web request to handle the metadata
     * @return Error Message With Status
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(WebRequest request) {
        return new ResponseEntity<>("Phone Number Already Present in AddressBook", HttpStatus.BAD_REQUEST);
    }
}
