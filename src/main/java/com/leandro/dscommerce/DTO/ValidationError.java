package com.leandro.dscommerce.DTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.leandro.dscommerce.Service.Exceptions.CustomError;

public class ValidationError extends CustomError {

    private List<FieldMessage> erros = new ArrayList<>();


    public ValidationError(Instant timeStamp, Integer status, String error, String path) {
        super(timeStamp, status, error, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void setErros(List<FieldMessage> erros) {
        this.erros = erros;
    }

    public void addError(String fieldName, String message){
        erros.add( new FieldMessage(fieldName, message));
    }
}
