package com.example.course.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private HttpStatus httpStatus;
    private CustomExceptionResponseBody body;

    CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.body = new CustomExceptionResponseBody(message);
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public CustomExceptionResponseBody getBody() {return this.body;
    }
}
