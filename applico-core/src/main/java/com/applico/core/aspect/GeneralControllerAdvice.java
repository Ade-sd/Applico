package com.applico.core.aspect;


import com.applico.core.utils.exception.ErrorResult;
import com.applico.core.utils.exception.SystemException;
import com.applico.core.utils.exception.statics.SystemError;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GeneralControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleUnHandledException(Exception exception, HttpServletResponse response) {
        log.error(exception.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new ErrorResult(SystemError.SERVER_ERROR, 5000, "server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorResult> handleSystemException(SystemException exception, HttpServletResponse response) {
        response.setStatus(exception.getErrorCode());
        return new ResponseEntity<>(new ErrorResult(exception), HttpStatus.valueOf(exception.getError().getValue()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResult>> constraintViolationException(MethodArgumentNotValidException exception, HttpServletResponse response) {
        List<ErrorResult> validationList = new ArrayList<>();
        for (FieldError eachFieldError : exception.getBindingResult().getFieldErrors()) {
            Map<String, Object> arguments = new HashMap<>();
            arguments.put("fieldName", eachFieldError.getField());
            arguments.put("error", eachFieldError.getDefaultMessage());
            arguments.put("rejectedValue", eachFieldError.getRejectedValue());
            validationList.add(new ErrorResult(SystemError.ILLEGAL_ARGUMENT, 5050, arguments));
        }
        response.setStatus(SystemError.ILLEGAL_ARGUMENT.getValue());
        return new ResponseEntity<>(validationList, HttpStatusCode.valueOf(SystemError.ILLEGAL_ARGUMENT.getValue()));
    }

}