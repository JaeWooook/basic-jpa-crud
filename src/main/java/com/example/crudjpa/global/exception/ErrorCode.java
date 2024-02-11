package com.example.crudjpa.global.exception;

public enum ErrorCode {
    POST_NOT_FOUND("게시글을 찾지 못했습니다."),
    COMMENT_NOT_FOUND("댓글을 찾지 못했습니다.");

    private final String statusMsg;

    ErrorCode(String statusMsg) {this.statusMsg = statusMsg;}

    public String getStatusMsg() {return this.statusMsg;}
}
