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



	public Instant getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}
    
    

}