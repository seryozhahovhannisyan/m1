package com.connectto.wallet.merchant.common.exception;

public class WalletApiException extends Exception {

    public WalletApiException() {
    }

    public WalletApiException(String message) {
        super(message);
    }

    public WalletApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletApiException(Throwable cause) {
        super(cause);
    }
}
