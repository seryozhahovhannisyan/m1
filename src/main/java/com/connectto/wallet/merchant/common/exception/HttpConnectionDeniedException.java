package com.connectto.wallet.merchant.common.exception;

public class HttpConnectionDeniedException extends Exception {

    public HttpConnectionDeniedException() {
    }

    public HttpConnectionDeniedException(String message) {
        super(message);
    }

    public HttpConnectionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConnectionDeniedException(Throwable cause) {
        super(cause);
    }
}
