package ccd.exception;

public class CcdRuntimerException extends RuntimeException {

    public CcdRuntimerException() {}

    public CcdRuntimerException(String msg) {
        super(msg);
    }

    public CcdRuntimerException(Throwable cause) {
        super(cause);
    }

    public CcdRuntimerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
