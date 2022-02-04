package com.example.course.exception;

public class ExceptionResponseBody {
    private String message;

    ExceptionResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
