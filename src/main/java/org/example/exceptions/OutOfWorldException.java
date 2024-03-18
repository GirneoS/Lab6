package org.example.exceptions;

/**
 * This exception is thrown when the user enters too small value for coordinate x/y while creating an instance of Coordinates class
 */
public class OutOfWorldException extends RuntimeException{

    public OutOfWorldException(String message) {
        super(message);
    }
}
