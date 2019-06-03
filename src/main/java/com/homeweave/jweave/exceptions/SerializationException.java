package com.homeweave.jweave.exceptions;

public class SerializationException extends WeaveException {
    public SerializationException(String msg, Throwable e) {
        super(msg, e);
    }

    public SerializationException(String msg) {
        super(msg);
    }
}
