package com.applico.core.utils.exception.statics;


import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public enum SystemError {
    SERVER_ERROR(HttpServletResponse.SC_SERVICE_UNAVAILABLE),
    REQUEST_EXPIRED(HttpServletResponse.SC_REQUEST_TIMEOUT),
    REQUIRED_FIELDS_NOT_SET(HttpServletResponse.SC_EXPECTATION_FAILED),
    IO_EXCEPTION(HttpServletResponse.SC_SERVICE_UNAVAILABLE),
    ILLEGAL_ARGUMENT(HttpServletResponse.SC_EXPECTATION_FAILED),
    INVALID_DATA_TYPE(HttpServletResponse.SC_EXPECTATION_FAILED),
    DATA_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND),
    VALIDATION_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED);


    private final Integer value;

    SystemError(Integer value) {
        this.value = value;
    }

}
