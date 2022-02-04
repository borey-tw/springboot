package com.example.course.exception;

import com.example.course.Course;
import org.springframework.http.HttpStatus;

public class CourseDuplicationExceptionResponse extends ExceptionResponseEntity {

    public CourseDuplicationExceptionResponse(Course course) {
        super(course.getTitle() + " is already exist.", HttpStatus.BAD_REQUEST);
    }
}
