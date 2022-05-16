package com.revature.ECommerce.exceptions;

public class InvalidOptionException extends RuntimeException{
    public InvalidOptionException(){

    }
    public InvalidOptionException(String message) {
        super(message);
    }
    public InvalidOptionException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidOptionException(Throwable cause) {
        super(cause);
    }

}
