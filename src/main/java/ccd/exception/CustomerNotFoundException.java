package ccd.exception;

import java.io.Serializable;

public class CustomerNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String msg) {
        super(msg);
    }

    public CustomerNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}