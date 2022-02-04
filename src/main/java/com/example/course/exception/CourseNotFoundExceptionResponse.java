package com.example.course.exception;

import org.springframework.http.HttpStatus;

public class CourseNotFoundExceptionResponse extends ExceptionResponseEntity {

    public CourseNotFoundExceptionResponse(Long id) {
        super("Could not find course " + id, HttpStatus.NOT_FOUND);
    }
}
