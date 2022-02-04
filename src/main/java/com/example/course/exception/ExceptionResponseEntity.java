package com.example.course.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponseEntity extends RuntimeException {

    private HttpStatus httpStatus;
    private ExceptionResponseBody body;

    ExceptionResponseEntity(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.body = new ExceptionResponseBody(message);
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ExceptionResponseBody getBody() {return this.body;
    }
}
