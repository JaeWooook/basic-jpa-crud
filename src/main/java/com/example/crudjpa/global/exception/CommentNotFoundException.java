package com.example.crudjpa.global.exception;

import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException{
    private ErrorCode errorCode;
    private String msg;

    public CommentNotFoundException(ErrorCode errorCode) {
        super(errorCode.getStatusMsg());
        this.errorCode = errorCode;
        this.msg = errorCode.getStatusMsg();
    }
}
