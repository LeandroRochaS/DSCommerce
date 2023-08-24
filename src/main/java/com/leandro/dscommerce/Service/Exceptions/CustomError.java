package com.leandro.dscommerce.Service.Exceptions;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomError {

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String path;
    
    public CustomError(Instant timeStamp, Integer status, String error, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

}