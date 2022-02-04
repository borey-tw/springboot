package com.example.course.exception;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class ExceptionResponseBody {
    private String message;

    @NotEmpty
    private Map<String, String> errorDetail;

    ExceptionResponseBody(String message) {
        this.message = message;
    }

    ExceptionResponseBody(String message, Map<String, String> error) {
        this.message = message;
        this.errorDetail = error;
    }

    public String getMessage() {
        return this.message;
    }

    public Map<String, String> getErrorDetail() {
        return this.errorDetail;
    }
}
