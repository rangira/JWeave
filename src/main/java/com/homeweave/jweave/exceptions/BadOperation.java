package com.homeweave.jweave.exceptions;

public class BadOperation extends WeaveException {
    public BadOperation(String msg, Throwable e) {
        super(msg, e);
    }

    public BadOperation(String msg) {
        super(msg);
    }
}
