package com.reactive.exception;

public class InSufficientFundsException extends RuntimeException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1680330976159313369L;

    public InSufficientFundsException(String message) {
        super(message);
    }

}
