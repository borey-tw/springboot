package com.example.course.exception;

import org.springframework.http.HttpStatus;

public class CourseNotFoundCustomException extends CustomException {

    public CourseNotFoundCustomException(Long id) {
        super("Could not find course " + id, HttpStatus.NOT_FOUND);
    }
}
