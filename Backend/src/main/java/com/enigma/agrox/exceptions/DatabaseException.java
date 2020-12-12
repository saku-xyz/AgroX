package com.enigma.agrox.exceptions;

public class DatabaseException extends RuntimeException{
	
	
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
    public DatabaseException(String message) {
        super(message);
    }
    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
