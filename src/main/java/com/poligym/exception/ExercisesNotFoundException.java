package com.poligym.exception;

public class ExercisesNotFoundException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExercisesNotFoundException() {
        super();
    }

    public ExercisesNotFoundException(String msg) {
        super(msg);
    }

    public ExercisesNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
