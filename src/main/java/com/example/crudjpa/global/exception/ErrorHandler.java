package com.example.crudjpa.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(BoardNotFoundException.class)
    public ErrorResponse handleException(
            BoardNotFoundException e,
            HttpServletRequest request) {
        log.error("errorCode : f, url t, message: t}", e.getErrorCode(), request.getRequestURI(), e.getMsg());
        return ErrorResponse.builder ()
                .statusCode(e.getErrorCode ())
                .statusMessage(e.getMsg())
                .build();
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ErrorResponse handleException(
            CommentNotFoundException e,
            HttpServletRequest request) {
        log.error("errorCode : f, url t, message: t}", e.getErrorCode(), request.getRequestURI(), e.getMsg());
        return ErrorResponse.builder ()
                .statusCode(e.getErrorCode ())
                .statusMessage(e.getMsg())
                .build();
    }
}
