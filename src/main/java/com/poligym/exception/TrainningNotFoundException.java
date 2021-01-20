package com.poligym.exception;


public class TrainningNotFoundException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = -9008121554939411166L;

    public TrainningNotFoundException() {
        super();
    }

    public TrainningNotFoundException(String msg) {
        super(msg);
    }

    public TrainningNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
