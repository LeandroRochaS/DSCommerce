package com.leandro.dscommerce.Service.Exceptions;

public class HttpMediaTypeNotAcceptableException  extends RuntimeException {
	    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HttpMediaTypeNotAcceptableException(String msg) {
			super(msg);
		}
	}
