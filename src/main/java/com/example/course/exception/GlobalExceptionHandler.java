package com.example.course.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler({CourseNotFoundException.class, CourseDuplicationException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    ErrorResponseBody handleCustomException(Exception e) {
//        return new ErrorResponseBody(e.getMessage());
//    }

    // Use return ResponseEntity
    @ExceptionHandler(ExceptionResponseEntity.class)
    protected ResponseEntity<ExceptionResponseBody> handleCustomException(ExceptionResponseEntity ex) {
        return new ResponseEntity<>(ex.getBody(), ex.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ExceptionResponseBody body = new ExceptionResponseBody("Validation failed", errors);

        return new ResponseEntity<Object>(body, headers, HttpStatus.BAD_REQUEST);
    }
}
