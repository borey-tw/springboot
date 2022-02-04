package com.example.course.exception;

import com.example.course.Course;
import org.springframework.http.HttpStatus;

public class CourseDuplicationCustomException extends CustomException {

    public CourseDuplicationCustomException(Course course) {
        super(course.getTitle() + " is already exist.", HttpStatus.BAD_REQUEST);
    }
}
