package ccd.exception;

import java.io.Serializable;

public class AddressNotFoundException  extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String msg) {
        super(msg);
    }

    public AddressNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}