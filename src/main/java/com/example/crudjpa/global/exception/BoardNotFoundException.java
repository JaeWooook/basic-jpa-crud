package com.example.crudjpa.global.exception;

import lombok.Getter;

@Getter
public class BoardNotFoundException extends RuntimeException{
    private ErrorCode errorCode;
    private String msg;

    public BoardNotFoundException(ErrorCode errorCode) {
        super (errorCode.getStatusMsg()); // runtimeException
        this.errorCode = errorCode;
        this.msg = errorCode.getStatusMsg();
    }
}
