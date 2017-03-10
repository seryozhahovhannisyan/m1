package com.connectto.wallet.merchant.common.exception;

/**
 * Created by Serozh on 6/21/2016.
 */

public class DataParseException extends Exception {


    public DataParseException() {
    }

    public DataParseException(Throwable cause) {
        super(cause);
    }

    public DataParseException(String message) {
        super(message);
    }

    public DataParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
