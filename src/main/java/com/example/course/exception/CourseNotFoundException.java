package com.example.course.exception;

import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends ExceptionResponseEntity {

    public CourseNotFoundException(Long id) {
        super("Could not find course " + id, HttpStatus.NOT_FOUND);
    }
}
