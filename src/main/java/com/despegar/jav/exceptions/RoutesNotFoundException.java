package com.despegar.jav.exceptions;


public class RoutesNotFoundException extends RuntimeException {

	public RoutesNotFoundException(String msg, Exception e) {
		super(msg, e);
	}
	

}
