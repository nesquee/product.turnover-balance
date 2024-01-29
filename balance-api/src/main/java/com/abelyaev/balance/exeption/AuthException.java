package com.abelyaev.balance.exeption;

public class AuthException extends ApiException {

    public AuthException(String message, String errorCode) {
        super(message, errorCode);
    }
}
