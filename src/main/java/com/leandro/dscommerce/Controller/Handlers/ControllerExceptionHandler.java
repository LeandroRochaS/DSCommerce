package com.leandro.dscommerce.Controller.Handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.leandro.dscommerce.DTO.ValidationError;
import com.leandro.dscommerce.Service.Exceptions.CustomError;
import com.leandro.dscommerce.Service.Exceptions.DataBaseException;
import com.leandro.dscommerce.Service.Exceptions.ResourceNotFoundException;



@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> customName(ResourceNotFoundException e,  javax.servlet.http.HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage() , request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomError> dataBase(DataBaseException e,  javax.servlet.http.HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // Use o status correto
        CustomError error = new CustomError( Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, javax.servlet.http.HttpServletRequest   request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // Use o status correto
        ValidationError error = new ValidationError( Instant.now(), status.value(), "dados inválidos", request.getRequestURI());

        for (FieldError f: e.getBindingResult().getFieldErrors()){
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }



}
