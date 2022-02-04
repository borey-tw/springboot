package com.example.course.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler({CourseNotFoundException.class, CourseDuplicationException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    ErrorResponseBody handleException(Exception e) {
//        return new ErrorResponseBody(e.getMessage());
//    }

    //Todo use Return with ResponseEntity
    @ExceptionHandler(ExceptionResponseEntity.class)
    ResponseEntity<ExceptionResponseBody> handleException(ExceptionResponseEntity ex) {
        return new ResponseEntity<>(ex.getBody(), ex.getHttpStatus());
    }
}
