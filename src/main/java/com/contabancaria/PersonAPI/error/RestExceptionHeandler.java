package com.contabancaria.PersonAPI.error;

import com.contabancaria.PersonAPI.exception.PersonNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHeandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> errors= new ArrayList<>();
        for(FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.add(error.getDefaultMessage());
        }
        for(ObjectError error: ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getObjectName()+": "+error.getDefaultMessage());
        }
        ApiError apiError=new ApiError(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),errors);
        return handleExceptionInternal(ex,apiError,headers,HttpStatus.BAD_REQUEST,request);

    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDuplicateRegister(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Cpf and/or E-mail attribute already registered in the database.");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ PersonNotFoundException.class })
    public ResponseEntity<Object> handlePessoaNotFoundException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ java.time.format.DateTimeParseException.class })
    public ResponseEntity<Object> handleDateFormatNotValid(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Date of birth is in invalid format");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
