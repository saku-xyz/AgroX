package com.enigma.agrox.exceptions;

public class InvalidParameterException extends RuntimeException{
	
	private static final long serialVersionUID = -9079454849611061074L;
	
    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidParameterException(String message) {
        super(message);
    }
    public InvalidParameterException(Throwable cause) {
        super(cause);
    }
	

}
