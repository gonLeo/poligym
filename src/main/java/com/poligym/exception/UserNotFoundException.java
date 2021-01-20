package com.poligym.exception;

public class UserNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -4134550929679192856L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
