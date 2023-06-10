package com.progettois.exception;

public class AlreadyExistsException extends Exception {
	
	public AlreadyExistsException() {}
	
	public AlreadyExistsException(String msg) {
		super(msg);
	}

}
