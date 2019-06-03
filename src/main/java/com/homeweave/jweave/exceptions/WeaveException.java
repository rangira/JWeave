package com.homeweave.jweave.exceptions;

public class WeaveException extends Exception {

    private String msg;

    public WeaveException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public WeaveException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
